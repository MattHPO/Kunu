package com.taipei.yanghaobo.kunu.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.taipei.yanghaobo.kunu.data.KunuRepository
import com.taipei.yanghaobo.kunu.db.DogEntry

/**
 * 狗狗完整資料 VM
 */
class DogDetailViewModel
/**
 * @param kunuRepository
 * Repository
 * @param id
 * 要查詢的狗狗 id
 */
  (kunuRepository: KunuRepository, id: Int) : ViewModel() {

  val oneDog: LiveData<DogEntry>

  init {
    oneDog = kunuRepository.getOneDog(id)
  }

}
