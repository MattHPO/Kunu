package com.taipei.yanghaobo.kunu.network

import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

internal object NetＷorkUtils {

  private val TAG = NetＷorkUtils::class.java.simpleName

  //    URL =========================================
  private val GOOGLE_BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?"

  //    PARAMETERS ==================================
  private val QUERY_PARAM = "q"

  private val MAX_RESULT_PARAM = "maxResults"

  private val PRINT_TYPE_PARAM = "printType"

  /**
   * 最大查詢數量
   */
  private val MAX_RESULT_VARIABLE = "10"
  //  ===============================================
  /**
   * @param queryString
   * 查詢書本的條件
   * @return bookJSONString
   * 回傳結果
   */
  fun getGoogleBookInfo(queryString: String): String? {
    var urlConnection: HttpURLConnection? = null
    var reader: BufferedReader? = null

    var bookJSONString: String? = null

    try {
      val queryURI = Uri.parse(GOOGLE_BOOK_BASE_URL).buildUpon()
        .appendQueryParameter(QUERY_PARAM, queryString)
        .appendQueryParameter(MAX_RESULT_PARAM, "10")
        .appendQueryParameter(PRINT_TYPE_PARAM, "books")
        .build()
      val requestURL = URL(queryURI.toString())

      urlConnection = requestURL.openConnection() as HttpURLConnection
      urlConnection.requestMethod = "GET"
      urlConnection.connect()

      val inputStream = urlConnection.inputStream

      reader = BufferedReader(InputStreamReader(inputStream))
      val stringBuilder = StringBuilder()

      var line = ""
      reader.readLine()?.let {
        stringBuilder.append(line)
        stringBuilder.append("\n")
      }

      //            沒有收到任何回應
      if (stringBuilder.length == 0) {
        return null
      }
      //            實際存檔地方
      bookJSONString = stringBuilder.toString()

    } catch (e: IOException) {
      e.printStackTrace()

    } finally {
      //
      urlConnection?.disconnect()
      if (reader != null) {
        try {
          reader.close()
        } catch (e: IOException) {
          e.printStackTrace()
        }

      }
    }

    Log.d(TAG, "getGoogleBookInfo: " + bookJSONString!!)
    return bookJSONString
  }
}
