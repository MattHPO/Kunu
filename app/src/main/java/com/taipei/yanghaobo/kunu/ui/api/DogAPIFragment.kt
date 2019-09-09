package com.taipei.yanghaobo.kunu.ui.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.taipei.yanghaobo.kunu.InjectorUtils
import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.Book

/**
 * 介接 Google APIs
 */
class DogAPIFragment : Fragment() {

  private var mBookQueryResultRV: RecyclerView? = null
  private var mTextInputSearch: TextInputLayout? = null
  private var mEditInputTextSearch: TextInputEditText? = null
  private var mBtSearch: MaterialButton? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_google_api, container, false)

    mBookQueryResultRV = view.findViewById(R.id.rv_book_query_result)
    mTextInputSearch = view.findViewById(R.id.text_input_search)
    mEditInputTextSearch = view.findViewById(R.id.edit_input_text_search)
    mBtSearch = view.findViewById(R.id.bt_search)

    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val adapter = BookListedAdapter(context)
    mBookQueryResultRV!!.adapter = adapter
    mBookQueryResultRV!!.layoutManager = LinearLayoutManager(context)
    val factory = InjectorUtils.provideBookListedVMFactory(context!!)
    val bookListedViewModel = ViewModelProviders.of(this, factory)
      .get(BookListedViewModel::class.java)
    bookListedViewModel.bookQueryResult.observe(
      this,
      Observer<List<Book>> { adapter.swapResult(it) })

    mBtSearch!!.setOnClickListener { bt ->
      val inputMethodManager = activity!!
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager?.hideSoftInputFromWindow(
        view.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
      )

      // 檢察網路連線
      val connMgr = activity!!
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      var networkInfo: NetworkInfo? = null
      if (connMgr != null) {
        networkInfo = connMgr.activeNetworkInfo
      }
      if (networkInfo != null && networkInfo.isConnected) {
        if (isQueryValid(mEditInputTextSearch!!.text)) {
          mTextInputSearch!!.error = null
          mTextInputSearch!!.hint = getString(R.string.query_google_book)
          // Network query
          bookListedViewModel.queryBookListed(mEditInputTextSearch!!.text!!.toString())
        } else {
          mTextInputSearch!!.error = getString(R.string.query_error)
          mTextInputSearch!!.hint = getString(R.string.input_something)
        }
      } else {
        mTextInputSearch!!.error = getString(R.string.error_no_internet)
        mTextInputSearch!!.hint = getString(R.string.error_hint_no_internet)
      }

    }
  }

  private fun isQueryValid(editable: Editable?): Boolean {
    return if (TextUtils.isEmpty(editable)) {
      false
    } else true
  }
}// Required empty public constructor
