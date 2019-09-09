package com.taipei.yanghaobo.kunu.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList

import com.taipei.yanghaobo.kunu.data.KunuRepository
import com.taipei.yanghaobo.kunu.db.DogListEntry

/**
 * 狗狗列表所需資訊 VM
 * (僅有 id, name_cn, photo_id)
 */
class DogCardViewModel(kunuRepository: KunuRepository) : ViewModel() {

  val allDogList: LiveData<PagedList<DogListEntry>>

  init {

    allDogList = kunuRepository.allDogList
  }
}
