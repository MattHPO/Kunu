package com.taipei.yanghaobo.kunu.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.taipei.yanghaobo.kunu.R;
import com.taipei.yanghaobo.kunu.db.DogEntry;
import com.taipei.yanghaobo.kunu.db.DogDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;


@Database(entities = {DogEntry.class}, version = 1, exportSchema = false)
public abstract class KunuDatabase extends RoomDatabase {

    private static final String TAG = "Matt" + KunuDatabase.class.getSimpleName();

    public static final String DB_NAME = "Kunu.db";

    public abstract DogDao getDogDao();

    private static KunuDatabase INSTANCE;

    private static final Object LOCK = new Object();

    private static Context sContext;

    public static KunuDatabase getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    Log.d(TAG, "Database INSTANCE created.");
                    sContext = context.getApplicationContext();
                    INSTANCE = Room.databaseBuilder(
                            sContext,
                            KunuDatabase.class,
                            DB_NAME)
                            .addCallback(sDogDbCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Kunu.db - DB Callback
     */
    @NonNull
    private static Callback sDogDbCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "Database invokes onCreate() Callback.");
            new PopulateDBAsync(INSTANCE).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d(TAG, "Database invokes onOpen() Callback.");
        }
    };

    /**
     * 實際 輸入 JSON 的 Method
     */
    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void>{

        private DogDao mDogDao;

        PopulateDBAsync(KunuDatabase kunuDatabase) {
            this.mDogDao = kunuDatabase.getDogDao();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... voids) {

//            FIXME: 確保資料乾淨
            mDogDao.deleteAll();

            InputStream inputStream = sContext.getResources().openRawResource(R.raw.dog);
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String inputLine;

            try {
                while ((inputLine = bufferedReader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("dog");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject element = jsonArray.getJSONObject(i);

                    mDogDao.insert(new DogEntry(
                            element.getString("name_cn"),
                            element.getString("name_en"),
                            element.getString("other_names"),
                            element.getString("nicknames"),
                            element.getString("origin"),
                            element.getString("type"),
                            element.getString("info"),
                            element.getInt("photo_id"),
                            new Date(),
                            element.getBoolean("is_valid")));
                }
            }
            catch (@NonNull IOException | JSONException ie){
                ie.printStackTrace();
            }

            return null;
        }
    }

}
