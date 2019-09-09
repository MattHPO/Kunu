package com.taipei.yanghaobo.kunu.ui.search

import android.content.Context
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
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.taipei.yanghaobo.kunu.InjectorUtils
import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.DogListEntry
import com.taipei.yanghaobo.kunu.ui.list.DogCardPagedListedAdapter

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(), DogCardPagedListedAdapter.DogCardOnClickedListener {

  private var mTextInputQuery: TextInputLayout? = null
  private var mEditInputTextQuery: TextInputEditText? = null
  private var mQueryResultList: RecyclerView? = null
  private var mBtQuery: MaterialButton? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_search_dog, container, false)

    mTextInputQuery = view.findViewById(R.id.text_input_query)
    mEditInputTextQuery = view.findViewById(R.id.edit_input_text_query)

    mQueryResultList = view.findViewById(R.id.rv_query_result_list)
    mQueryResultList!!.setHasFixedSize(true)

    mBtQuery = view.findViewById(R.id.bt_search)


    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    mQueryResultList!!.layoutManager = LinearLayoutManager(context)
    val adapter = DogCardPagedListedAdapter(context)
    adapter.setDogCardOnClickedListener(this)
    mQueryResultList!!.adapter = adapter

    val factory = InjectorUtils.provideSearchVMFactory(context!!)
    val searchViewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
    searchViewModel.queryResultList.observe(
      this,
      Observer { dogListEntries -> adapter.submitList(dogListEntries) })

    mBtQuery!!.setOnClickListener { bt ->
      val inputMethodManager =
        activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager?.hideSoftInputFromWindow(
        view.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
      )

      if (isQueryValid(mEditInputTextQuery!!.text)) {
        mTextInputQuery!!.error = null
        mTextInputQuery!!.hint = getString(R.string.input_query_word)
        searchViewModel.queryDog(mEditInputTextQuery!!.text!!.toString())
      } else {
        mTextInputQuery!!.error = getString(R.string.query_error)
        mTextInputQuery!!.hint = getString(R.string.input_something)
      }
    }
  }

  private fun isQueryValid(editable: Editable?): Boolean {

    return if (TextUtils.isEmpty(editable)) {
      false
    } else true
  }

  override fun onDogItemClick(dogEntry: DogListEntry) {

  }
}// Required empty public constructor
