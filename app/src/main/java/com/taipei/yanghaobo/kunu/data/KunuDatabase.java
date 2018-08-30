package com.taipei.yanghaobo.kunu.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.taipei.yanghaobo.kunu.db.DogEntry;
import com.taipei.yanghaobo.kunu.model.DogDao;

@Database(entities = {DogEntry.class}, version = 1)
public abstract class KunuDatabase extends RoomDatabase {

    public static final String DB_NAME = "Kunu.db";

    public abstract DogDao DogDao();

    private static KunuDatabase INSTANCE;

    private static final Object LOCK = new Object();

    public static KunuDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            KunuDatabase.class,
                            DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
