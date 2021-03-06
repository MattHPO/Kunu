package com.taipei.yanghaobo.kunu.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.taipei.yanghaobo.kunu.data.KunuRepository

class DogDetailVMFactory(private val mKunuRepository: KunuRepository, private val mId: Int) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {

    return DogDetailViewModel(mKunuRepository, mId) as T
  }
}
