package com.taipei.yanghaobo.kunu.ui.search

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

import com.taipei.yanghaobo.kunu.data.KunuRepository
import com.taipei.yanghaobo.kunu.db.DogListEntry

class SearchViewModel(kunuRepository: KunuRepository) : ViewModel() {

  private val mQueryText = MutableLiveData<String>()
  /**
   * 取得查詢結果
   * @return
   * 查詢結果列表
   */
  val queryResultList: LiveData<PagedList<DogListEntry>>

  init {

    queryResultList =
      Transformations.switchMap(mQueryText) { input -> kunuRepository.getDogByCNName(input) }
  }

  /**
   * 查詢
   *
   * @param queryText
   * 查詢關鍵字
   */
  fun queryDog(queryText: String) {
    mQueryText.value = queryText
  }
}
