package com.taipei.yanghaobo.kunu.network

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.taipei.yanghaobo.kunu.KunuExecutors
import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.Book
import com.taipei.yanghaobo.kunu.service.GoogleAPIJobIntentService
import java.util.ArrayList
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * 取 https://developers.google.com/apis-explorer/#p/ 中
 * Google APIs Explorer 網頁
 */
class GoogleNetWorkDataSoruce private constructor(
  /**
   * ApplicationContext
   */
  private val mContext: Context, private val mKunuExecutors: KunuExecutors
) {

  private val mBookList: MutableLiveData<List<Book>>

  private val mRetrofitService: RetrofitService

  init {
    mBookList = MutableLiveData()
    mRetrofitService = RetrofitClient.instance.retrofitService
  }

  /**
   * 取得 書本的 liveData
   *
   * @return
   * Google 書籍資料
   */
  fun getBookList(query: String): MutableLiveData<List<Book>> {
    startFetchGoogleBookService(query)
    return mBookList
  }

  /**
   * 啟動 IntentService 進行背景工作
   * @param query
   * 查詢 book 的 query
   */
  private fun startFetchGoogleBookService(query: String) {
    val intent = GoogleAPIJobIntentService.getJobIntent(query)
    GoogleAPIJobIntentService.enqueueWork(mContext.applicationContext, intent)
  }

  /**
   * 取得 book 的邏輯
   */
  fun fetchBook(query: String) {
    mKunuExecutors.networkIO.execute {

      val googleBookInfo = NetＷorkUtils.getGoogleBookInfo(query)
      val bookList = ArrayList<Book>()

      try {
        val jsonObject = JSONObject(googleBookInfo)
        val jsonArray = jsonObject.getJSONArray("items")

        var title: String? = null
        var authors: String? = null

        for (i in 0 until jsonArray.length()) {
          val book = jsonArray.getJSONObject(i)
          val volumeInfo = book.getJSONObject("volumeInfo")
          var eachBook: Book? = null

          if (!volumeInfo.isNull("title") && !volumeInfo.isNull("authors")) {
            title = volumeInfo.getString("title")
            authors = volumeInfo.getString("authors")

            if (title != null && authors != null) {
              //                            FIXME
              authors = authors.substring(2, authors.length - 2)

              eachBook = Book(title, authors)
            } else {
              eachBook = Book(" :( ", mContext.getString(R.string.fetch_data_fail))
            }
            bookList.add(eachBook)
          }
        }

        if (bookList.size > 0) {
          mBookList.postValue(bookList)
        }
      } catch (e: JSONException) {
        e.printStackTrace()
      }
    }
  }

  companion object {

    private val TAG = GoogleNetWorkDataSoruce::class.java.simpleName
    private val LOCK = Any()

    private var sInstance: GoogleNetWorkDataSoruce? = null

    fun getInstance(context: Context, kunuExecutors: KunuExecutors): GoogleNetWorkDataSoruce {
      if (sInstance == null) {
        synchronized(LOCK) {
          if (sInstance == null) {
            sInstance = GoogleNetWorkDataSoruce(context, kunuExecutors)
          }
        }
      }
      return sInstance
    }
  }
}
