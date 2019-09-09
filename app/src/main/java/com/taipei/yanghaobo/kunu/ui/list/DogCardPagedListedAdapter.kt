package com.taipei.yanghaobo.kunu.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.DogListEntry

class DogCardPagedListedAdapter(context: Context) :
  PagedListAdapter<DogListEntry, DogCardPagedListedAdapter.DogCardViewHolder>(sDogListDiffUtil) {

  private val mInflater: LayoutInflater

  private var mDogCardOnClickedListener: DogCardOnClickedListener? = null

  interface DogCardOnClickedListener {
    fun onDogItemClick(dogEntry: DogListEntry)
  }

  init {
    //        設定 DiffUtil 進行 資料更新時的計算
    mInflater = LayoutInflater.from(context)
  }

  internal inner class DogCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //        final CardView mCardView;
    private val mImageView: ImageView
    private val mTextView: TextView

    init {
      //            mCardView = itemView.findViewById(R.id.dog_card);
      this.mImageView = itemView.findViewById(R.id.img_dog_photo)
      this.mTextView = itemView.findViewById(R.id.tv_dog_name)
    }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DogCardViewHolder {
    val itemView = mInflater.inflate(R.layout.kunu_dog_card, viewGroup, false)

    itemView.findViewById<View>(R.id.tv_dog_name).setOnClickListener { v ->
      if (mDogCardOnClickedListener != null) {
        mDogCardOnClickedListener!!.onDogItemClick(itemView.tag as DogListEntry)
      }
    }
    return DogCardViewHolder(itemView)
  }

  override fun onBindViewHolder(dogCardViewHolder: DogCardViewHolder, i: Int) {
    val dogListEntry = getItem(i)
    if (dogListEntry != null) {
      dogCardViewHolder.mTextView.text = dogListEntry.name_cn
      dogCardViewHolder.mImageView.setImageResource(R.drawable.kunu_snow_shake)
      dogCardViewHolder.itemView.tag = dogListEntry
    } else {
      dogCardViewHolder.mTextView.text = "沒有資料"
      dogCardViewHolder.mImageView.setImageResource(R.drawable.kunu_snow_full)
    }
  }
  //

  //
  fun setDogCardOnClickedListener(listener: DogCardOnClickedListener) {
    this.mDogCardOnClickedListener = listener
  }

  companion object {

    private val sDogListDiffUtil = object : DiffUtil.ItemCallback<DogListEntry>() {
      override fun areItemsTheSame(
        dogListEntry:/* old */ DogListEntry,
        t1:/* new */ DogListEntry
      ): Boolean {
        return dogListEntry.id == t1.id
      }

      override fun areContentsTheSame(
        dogListEntry:/* old */ DogListEntry,
        t1:/* new */ DogListEntry
      ): Boolean {
        return dogListEntry.name_cn == t1.name_cn
      }
    }
  }
}
