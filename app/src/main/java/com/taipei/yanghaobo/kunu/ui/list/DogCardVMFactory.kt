package com.taipei.yanghaobo.kunu.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.taipei.yanghaobo.kunu.data.KunuRepository

class DogCardVMFactory(private val mKunuRepository: KunuRepository) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {

    return DogCardViewModel(mKunuRepository) as T
  }
}
