package com.taipei.yanghaobo.kunu

import android.content.Context

import com.taipei.yanghaobo.kunu.data.KunuDatabase
import com.taipei.yanghaobo.kunu.data.KunuRepository
import com.taipei.yanghaobo.kunu.network.GoogleNetWorkDataSoruce
import com.taipei.yanghaobo.kunu.ui.api.BookListedVMFactory
import com.taipei.yanghaobo.kunu.ui.detail.DogDetailVMFactory
import com.taipei.yanghaobo.kunu.ui.edit.DogDetailEditVMFactory
import com.taipei.yanghaobo.kunu.ui.list.DogCardVMFactory
import com.taipei.yanghaobo.kunu.ui.search.SearchVMFactory

object InjectorUtils {

  fun provideKunuRepository(context: Context): KunuRepository {
    val kunuDb = KunuDatabase.getInstance(context.applicationContext)
    val executors = KunuExecutors.instance
    val googleNetWorkDataSoruce =
      GoogleNetWorkDataSoruce.getInstance(context.applicationContext, executors)
    return KunuRepository.getInstance(kunuDb.dogDao, executors, googleNetWorkDataSoruce)
  }

  fun provideGoogleNetWorkDataSoruce(context: Context): GoogleNetWorkDataSoruce {
    // 在某些情況下如從 service 啟動 App ，Repo可能還不存在，預防
    provideKunuRepository(context)

    val kunuExecutors = KunuExecutors.instance
    return GoogleNetWorkDataSoruce.getInstance(context.applicationContext, kunuExecutors)
  }

  fun provideDogCardVMFactory(context: Context): DogCardVMFactory {
    val kunuRepository = provideKunuRepository(context.applicationContext)
    return DogCardVMFactory(kunuRepository)
  }

  fun provideDogDetailVMFactory(context: Context, id: Int): DogDetailVMFactory {
    val kunuRepository = provideKunuRepository(context.applicationContext)
    return DogDetailVMFactory(kunuRepository, id)
  }

  fun provideSearchVMFactory(context: Context): SearchVMFactory {
    val kunuRepository = provideKunuRepository(context.applicationContext)
    return SearchVMFactory(kunuRepository)
  }

  fun provideDogDetailEditVMFactory(context: Context, id: Int): DogDetailEditVMFactory {
    val kunuRepository = provideKunuRepository(context.applicationContext)
    return DogDetailEditVMFactory(kunuRepository, id)
  }

  fun provideBookListedVMFactory(context: Context): BookListedVMFactory {
    val kunuRepository = provideKunuRepository(context.applicationContext)
    return BookListedVMFactory(kunuRepository)
  }

}
