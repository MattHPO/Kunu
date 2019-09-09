package com.taipei.yanghaobo.kunu.ui.edit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.taipei.yanghaobo.kunu.InjectorUtils
import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.DogEntry

class DogDetailEditFragment : Fragment() {

  private var mDogEntry: DogEntry? = null
  private var mTvDogNameEN: EditText? = null
  private var mTvDogNameCN: EditText? = null
  private var mTvDogNickname: EditText? = null
  private var mTvDogType: EditText? = null
  private var mTvDogInfo: EditText? = null
  private var mTvDogOrigin: EditText? = null
  private var mBtUpdate: Button? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.dog_detail_edit_fragment, container, false)

    mTvDogNameEN = view.findViewById(R.id.tv_dog_name_en)
    mTvDogNameCN = view.findViewById(R.id.tv_dog_name_cn)
    mTvDogNickname = view.findViewById(R.id.tv_dog_nicknames)
    mTvDogType = view.findViewById(R.id.tv_dog_type)
    mTvDogInfo = view.findViewById(R.id.tv_dog_info)
    mTvDogOrigin = view.findViewById(R.id.tv_dog_origin)
    mBtUpdate = view.findViewById(R.id.bt_update)

    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val dogDetailEditFragmentArgs = DogDetailEditFragmentArgs.fromBundle(arguments!!)
    val argsId = dogDetailEditFragmentArgs.id

    val factory = InjectorUtils.provideDogDetailEditVMFactory(context!!, argsId)
    val dogDetailEditViewModel = ViewModelProviders
      .of(this, factory)
      .get(DogDetailEditViewModel::class.java)
    dogDetailEditViewModel.dogEntry.observe(this, { dogEntry ->
      if (dogEntry != null) {
        mDogEntry = dogEntry
        setDogDetail()
      }
    })

    mBtUpdate!!.setOnClickListener { bt ->
      val inputMethodManager =
        activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager?.hideSoftInputFromWindow(
        view.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
      )


      updateViewObject()
      dogDetailEditViewModel.updateDogDetail(mDogEntry)
      Navigation.findNavController(getView()!!).navigateUp()
    }
  }

  /**
   * 將編輯好的文字，存入Entity
   */
  private fun updateViewObject() {
    val name_cn = mTvDogNameCN!!.text.toString()
    val name_en = mTvDogNameEN!!.text.toString()
    val nicknames = mTvDogNickname!!.text.toString()
    val type = mTvDogType!!.text.toString()
    val info = mTvDogInfo!!.text.toString()
    val origin = mTvDogOrigin!!.text.toString()
    mDogEntry!!.name_cn = name_cn
    mDogEntry!!.name_en = name_en
    mDogEntry!!.nicknames = nicknames
    mDogEntry!!.type = type
    mDogEntry!!.info = info
    mDogEntry!!.origin = origin
  }

  /**
   * 將 Entity 文字放入 畫面
   */
  private fun setDogDetail() {
    val name_cn = mDogEntry!!.name_cn
    val name_en = mDogEntry!!.name_en
    val nicknames = mDogEntry!!.nicknames
    val type = mDogEntry!!.type
    val info = mDogEntry!!.info
    val origin = mDogEntry!!.origin
    mTvDogNameEN!!.setText(name_en)
    mTvDogNameCN!!.setText(name_cn)
    mTvDogNickname!!.setText(nicknames)
    mTvDogType!!.setText(type)
    mTvDogInfo!!.setText(info)
    mTvDogOrigin!!.setText(origin)
  }

  companion object {

    fun newInstance(): DogDetailEditFragment {
      return DogDetailEditFragment()
    }
  }
}
