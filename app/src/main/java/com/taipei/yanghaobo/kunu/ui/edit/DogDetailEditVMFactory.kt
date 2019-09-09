package com.taipei.yanghaobo.kunu.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.taipei.yanghaobo.kunu.data.KunuRepository

class DogDetailEditVMFactory(private val mKunuRepository: KunuRepository, private val mId: Int) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return DogDetailEditViewModel(mKunuRepository, mId) as T
  }
}
