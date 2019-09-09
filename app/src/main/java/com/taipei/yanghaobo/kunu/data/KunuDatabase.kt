package com.taipei.yanghaobo.kunu.data

import android.content.Context
import android.os.AsyncTask
import android.util.Log

import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.DogDao
import com.taipei.yanghaobo.kunu.db.DogEntry

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.ref.WeakReference
import java.util.Date
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [DogEntry::class], version = 1, exportSchema = false)
abstract class KunuDatabase : RoomDatabase() {

  abstract val dogDao: DogDao

  /**
   * 實際 輸入 JSON 的 Method
   */
  private class PopulateDBAsync internal constructor(kunuDatabase: KunuDatabase) :
    AsyncTask<Void, Void, Void>() {

    private val mDogDao: DogDao

    init {
      this.mDogDao = kunuDatabase.dogDao
    }

    override fun doInBackground(vararg voids: Void): Void? {

      //            FIXME: 確保資料乾淨
      mDogDao.deleteAll()

      val inputStream = sContext!!.get().getResources().openRawResource(R.raw.dog)
      val stringBuilder = StringBuilder()
      val bufferedReader = BufferedReader(InputStreamReader(inputStream))
      var inputLine: String

      try {
        while ((inputLine = bufferedReader.readLine()) != null) {
          stringBuilder.append(inputLine)
        }
        val jsonObject = JSONObject(stringBuilder.toString())
        val jsonArray = jsonObject.getJSONArray("dog")
        for (i in 0 until jsonArray.length()) {
          val element = jsonArray.getJSONObject(i)

          mDogDao.insert(
            DogEntry(
              element.getString("name_cn"),
              element.getString("name_en"),
              element.getString("other_names"),
              element.getString("nicknames"),
              element.getString("origin"),
              element.getString("type"),
              element.getString("info"),
              element.getInt("photo_id"),
              Date(),
              element.getBoolean("is_valid")
            )
          )
        }
      } catch (ie: IOException) {
        ie.printStackTrace()
      } catch (ie: JSONException) {
        ie.printStackTrace()
      }

      return null
    }
  }

  companion object {

    private val TAG = "Matt" + KunuDatabase::class.java.simpleName

    val DB_NAME = "Kunu.db"

    private var INSTANCE: KunuDatabase? = null

    private val LOCK = Any()

    private var sContext: WeakReference<Context>? = null

    fun getInstance(context: Context): KunuDatabase {
      if (INSTANCE == null) {
        synchronized(LOCK) {
          if (INSTANCE == null) {
            Log.d(TAG, "Database INSTANCE created.")
            sContext = WeakReference(context.applicationContext)
            INSTANCE = Room.databaseBuilder(
              sContext!!.get(),
              KunuDatabase::class.java,
              DB_NAME
            )
              .addCallback(sDogDbCallback)
              .build()
          }
        }
      }
      return INSTANCE
    }

    /**
     * Kunu.db - DB Callback
     */
    private val sDogDbCallback = object : RoomDatabase.Callback() {
      override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Log.d(TAG, "Database invokes onCreate() Callback.")
        PopulateDBAsync(INSTANCE!!).execute()
      }

      override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        Log.d(TAG, "Database invokes onOpen() Callback.")
      }
    }
  }

}
