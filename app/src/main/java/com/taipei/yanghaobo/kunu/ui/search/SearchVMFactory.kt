package com.taipei.yanghaobo.kunu.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.taipei.yanghaobo.kunu.data.KunuRepository

class SearchVMFactory(private val mKunuRepository: KunuRepository) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {

    return SearchViewModel(mKunuRepository) as T
  }
}
