package com.taipei.yanghaobo.kunu.ui.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.taipei.yanghaobo.kunu.data.KunuRepository
import com.taipei.yanghaobo.kunu.db.Book
import com.taipei.yanghaobo.kunu.db.GoogleBookGson
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookListedViewModel(private val mKunuRepository: KunuRepository) : ViewModel() {

  private val mBookQueryText = MutableLiveData<String>()
  val bookQueryResult: LiveData<List<Book>>

  private val mCompositeDisposable = CompositeDisposable()

  init {
    bookQueryResult = Transformations
      .switchMap(
        mBookQueryText,
        Function<String, LiveData<List<Book>>> { mKunuRepository.fetchBook(it) })
  }

  fun queryBookListed(query: String) {
    mBookQueryText.value = query

    // FIXME: For test
    mKunuRepository.getBookListByRetrofit(query)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(object : SingleObserver<GoogleBookGson> {
        override fun onSubscribe(d: Disposable) {
          mCompositeDisposable.add(d)
        }

        override fun onSuccess(googleBookGson: GoogleBookGson) {
          Log.d("Matt", "onSuccess: $googleBookGson")
        }

        override fun onError(e: Throwable) {
          Log.d("Matt", "onError: " + e.message)
        }
      })
  }
}
