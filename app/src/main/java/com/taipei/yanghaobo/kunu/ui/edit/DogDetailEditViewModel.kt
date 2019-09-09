package com.taipei.yanghaobo.kunu.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.taipei.yanghaobo.kunu.data.KunuRepository
import com.taipei.yanghaobo.kunu.db.DogEntry

class DogDetailEditViewModel
/**
 * @param kunuRepository
 * @param id
 */
  (private val mKunuRepository: KunuRepository, id: Int) : ViewModel() {
  /**
   * 取得狗狗資料
   *
   * @return
   * 狗狗詳細資料
   */
  val dogEntry: LiveData<DogEntry>

  init {
    this.dogEntry = mKunuRepository.getOneDog(id)
  }

  /**
   * 更新狗狗資料
   *
   * @param dogEntry
   * 被更新的資料
   */
  fun updateDogDetail(dogEntry: DogEntry) {
    mKunuRepository.updateDog(dogEntry)
  }
}
