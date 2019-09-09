package com.taipei.yanghaobo.kunu.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.taipei.yanghaobo.kunu.InjectorUtils
import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.DogEntry

import java.util.Objects

/**
 */
class DogDetailFragment : Fragment() {

  private var mDogEntry: DogEntry? = null
  private var mTvDogNameEN: TextView? = null
  private var mTvDogNameCN: TextView? = null
  private var mTvDogNickname: TextView? = null
  private var mTvDogType: TextView? = null
  private var mTvDogInfo: TextView? = null
  private var mTvDogOrigin: TextView? = null
  private var mFabEditDog: FloatingActionButton? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    setHasOptionsMenu(true)
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.kunu_dog_detail_fragment, container, false)
    //
    //        if (getArguments() != null) {
    //            mDogEntry = getArguments().getParcelable(ARG_PARAM1);
    //        }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      view.findViewById<View>(R.id.kunu_dog_detail_csl).background =
        resources.getDrawable(R.drawable.kunu_dog_detail_background_shape, null)
    } else {
      view.findViewById<View>(R.id.kunu_dog_detail_csl).background =
        resources.getDrawable(R.drawable.kunu_dog_detail_background_shape)
    }

    mTvDogNameEN = view.findViewById(R.id.tv_dog_name_en)
    mTvDogNameCN = view.findViewById(R.id.tv_dog_name_cn)
    mTvDogNickname = view.findViewById(R.id.tv_dog_nicknames)
    mTvDogType = view.findViewById(R.id.tv_dog_type)
    mTvDogInfo = view.findViewById(R.id.tv_dog_info)
    mTvDogOrigin = view.findViewById(R.id.tv_dog_origin)
    mFabEditDog = view.findViewById(R.id.fab_edit_dog)

    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // Navigation Component safeArgs
    val safeArgs = DogDetailFragmentArgs.fromBundle(arguments!!)
    val argsId = safeArgs.id
    //        FIXME: for test
    Toast.makeText(context, "SafeArg ID is : $argsId", Toast.LENGTH_SHORT).show()
    //
    val dogDetailVMFactory = InjectorUtils.provideDogDetailVMFactory(context!!, argsId)
    val detailViewModel = ViewModelProviders
      .of(this, dogDetailVMFactory)
      .get(DogDetailViewModel::class.java)
    detailViewModel.oneDog.observe(this, { dogEntry ->
      mDogEntry = dogEntry
      if (mDogEntry != null) {
        Toast.makeText(context, "選到的狗狗是～ " + mDogEntry!!.name_cn + "!", Toast.LENGTH_SHORT).show()
        setDogDetail()
      }
    })

    mFabEditDog!!.setOnClickListener { fab ->
      val action = DogDetailFragmentDirections.actionDogDetailFragmentToDogDetailEditFragment()
      action.id = argsId
      Navigation.findNavController(Objects.requireNonNull<View>(getView())).navigate(action)
    }
  }

  private fun setDogDetail() {
    val name_cn = mDogEntry!!.name_cn
    val name_en = StringBuilder(mDogEntry!!.name_en!!)
    name_en.insert(0, getString(R.string.kunu_label_name_en) + " ")
    val nicknames = StringBuilder(mDogEntry!!.nicknames!!)
    nicknames.insert(0, getString(R.string.kunu_label_nicknames) + " ")
    val type = StringBuilder(mDogEntry!!.type!!)
    type.insert(0, getString(R.string.kunu_label_type) + " ")
    val info = StringBuilder(mDogEntry!!.info!!)
    if (TextUtils.isEmpty(info.toString())) {
      info.insert(0, getString(R.string.dog_info_null))
    }
    info.insert(0, getString(R.string.kunu_label_info) + " ")
    val origin = StringBuilder(mDogEntry!!.origin!!)
    origin.insert(0, getString(R.string.kunu_label_origin) + " ")

    mTvDogNameEN!!.text = name_en
    mTvDogNameCN!!.text = name_cn
    mTvDogNickname!!.text = nicknames.toString()
    mTvDogType!!.text = type.toString()
    mTvDogInfo!!.text = info.toString()
    mTvDogOrigin!!.text = origin.toString()
  }

  companion object {

    private val ARG_PARAM1 = "param1"

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param dogEntry Parameter 1.
     * @return A new instance of fragment DogDetailFragment.
     */
    fun newInstance(dogEntry: DogEntry): DogDetailFragment {
      val fragment = DogDetailFragment()
      val args = Bundle()
      args.putParcelable(ARG_PARAM1, dogEntry)
      fragment.arguments = args
      return fragment
    }
  }
}// Required empty public constructor
