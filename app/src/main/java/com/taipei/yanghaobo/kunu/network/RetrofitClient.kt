package com.taipei.yanghaobo.kunu.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit2 Client端 configuration
 *
 * @author yanghaobo
 */
class RetrofitClient private constructor() {

  val retrofitService: RetrofitService

  init {

    val client = OkHttpClient.Builder()
      .addInterceptor(LoggingInterceptor())
      .build()

    val retrofit = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl("https://www.googleapis.com/")
      .client(client)
      .build()

    retrofitService = retrofit.create(RetrofitService::class.java)
  }

  companion object {

    private val LOCK = Any()

    private var sInstance: RetrofitClient? = null

    val instance: RetrofitClient
      get() {
        if (sInstance == null) {
          synchronized(LOCK) {
            if (sInstance == null) {
              sInstance = RetrofitClient()
            }
          }
        }
        return sInstance
      }
  }
}
