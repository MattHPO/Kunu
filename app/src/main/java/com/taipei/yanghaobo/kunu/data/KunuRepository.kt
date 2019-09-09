package com.taipei.yanghaobo.kunu.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.taipei.yanghaobo.kunu.KunuExecutors
import com.taipei.yanghaobo.kunu.db.Book
import com.taipei.yanghaobo.kunu.db.DogDao
import com.taipei.yanghaobo.kunu.db.DogEntry
import com.taipei.yanghaobo.kunu.db.DogListEntry
import com.taipei.yanghaobo.kunu.db.GoogleBookGson
import com.taipei.yanghaobo.kunu.network.GoogleNetWorkDataSoruce
import com.taipei.yanghaobo.kunu.network.RetrofitClient
import com.taipei.yanghaobo.kunu.network.RetrofitService
import io.reactivex.Single

/**
 * 管理所有 Data source
 * 制定 資源API 給其他 component 使用
 */
class KunuRepository private constructor(// Resource APIs
  private val mDogDao: DogDao, // ThreadPools
  private val mKunuExecutors: KunuExecutors,
  private val mGoogleNetWorkDataSoruce: GoogleNetWorkDataSoruce
) {
  private val mRetrofitService: RetrofitService

  /**
   * 取得所有狗狗資料
   *
   * @return 狗狗圖鑑全
   */
  //        return (LiveData<PagedList<DogEntry>>) new LivePagedListBuilder(factory, sPageSize).build();
  val dogAll: LiveData<PagedList<DogEntry>>
    get() {

      val factory = mDogDao.all
      return LivePagedListBuilder(
        factory,
        PagedList.Config.Builder()
          .setPageSize(sPageSize)
          .setInitialLoadSizeHint(sInitialLoadSize)
          .setEnablePlaceholders(false)
          .build()
      )
        .build()
    }

  val allDogList: LiveData<PagedList<DogListEntry>>
    get() {

      val factory = mDogDao.allDogList
      return LivePagedListBuilder(
        factory,
        PagedList.Config.Builder()
          .setPageSize(5)
          .setInitialLoadSizeHint(10)
          .setEnablePlaceholders(true)
          .build()
      )
        .build()
    }

  init {
    this.mRetrofitService = RetrofitClient.instance.retrofitService
  }

  /**
   * 用 id 取得 單隻 狗狗資料
   *
   * @param id raw id
   * @return 符合的狗狗資料
   */
  fun getOneDog(id: Int): LiveData<DogEntry> {
    return mDogDao.getOneDog(id)
  }

  /**
   * 用關鍵字搜尋狗狗
   *
   * @param keyWord 關鍵字
   * @return 搜尋結果
   */
  fun getDogByCNName(keyWord: String): LiveData<PagedList<DogListEntry>> {
    val factory = mDogDao.getByCNName(keyWord)


    return LivePagedListBuilder(
      factory,
      PagedList.Config.Builder()
        .setPageSize(5)
        .setInitialLoadSizeHint(10)
        .setEnablePlaceholders(true)
        .build()
    )
      .build()
  }

  /**
   * 新增狗狗資料
   *
   * @param dogEntry
   * 狗狗資料，可為多筆
   */
  fun addNewDog(vararg dogEntry: DogEntry) {
    mKunuExecutors.diskIO.execute { mDogDao.insert(*dogEntry) }
  }

  /**
   * 更新新狗狗資料
   *
   * @param dogEntry
   * 狗狗資料，可為多筆
   */
  fun updateDog(dogEntry: DogEntry) {
    mKunuExecutors.diskIO.execute { mDogDao.updateDog(dogEntry) }
  }

  fun fetchBook(query: String): LiveData<List<Book>> {
    return mGoogleNetWorkDataSoruce.getBookList(query)
  }

  fun getBookListByRetrofit(queryString: String): Single<GoogleBookGson> {
    return mRetrofitService.getGoogleBookList(10, "books", queryString)
  }

  companion object {
    // Singleton
    private var INSTANCE: KunuRepository? = null
    private val LOCK = Any()
    // for Paging
    private val sPageSize = 10
    private val sInitialLoadSize = 10

    fun getInstance(
      dogDao: DogDao,
      executors: KunuExecutors,
      googleNetWorkDataSoruce: GoogleNetWorkDataSoruce
    ): KunuRepository {
      if (INSTANCE == null) {
        synchronized(LOCK) {
          if (INSTANCE == null) {
            INSTANCE = KunuRepository(dogDao, executors, googleNetWorkDataSoruce)
          }
        }
      }
      return INSTANCE
    }
  }
}
