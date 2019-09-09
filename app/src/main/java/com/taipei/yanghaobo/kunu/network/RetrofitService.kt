package com.taipei.yanghaobo.kunu.network

import com.taipei.yanghaobo.kunu.db.GoogleBookGson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

  @GET("books/v1/volumes")
  fun getGoogleBookList(
    @Query("maxResults") resultSize: Int,
    @Query("printType") printType: String, @Query("q") keyword: String
  ): Single<GoogleBookGson>
}
