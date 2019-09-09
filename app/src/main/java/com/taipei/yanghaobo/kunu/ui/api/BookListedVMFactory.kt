package com.taipei.yanghaobo.kunu.ui.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.taipei.yanghaobo.kunu.data.KunuRepository

class BookListedVMFactory(private val mKunuRepository: KunuRepository) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {

    return BookListedViewModel(mKunuRepository) as T
  }
}
