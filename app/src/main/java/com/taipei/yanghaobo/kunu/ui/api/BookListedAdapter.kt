package com.taipei.yanghaobo.kunu.ui.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.taipei.yanghaobo.kunu.R
import com.taipei.yanghaobo.kunu.db.Book

class BookListedAdapter(context: Context?) :
  RecyclerView.Adapter<BookListedAdapter.SearchViewHolder>() {

  private val mInflater: LayoutInflater

  private var mBooksResult: List<Book>? = null

  init {
    this.mInflater = LayoutInflater.from(context)
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SearchViewHolder {

    val view = mInflater.inflate(R.layout.kunu_book_item, viewGroup, false)
    return SearchViewHolder(view)
  }

  override fun onBindViewHolder(searchViewHolder: SearchViewHolder, i: Int) {
    val book = mBooksResult!![i]
    searchViewHolder.mBookAuthor.text = book.bookAuthor
    searchViewHolder.mBookName.text = book.bookName
  }

  override fun getItemCount(): Int {
    return if (mBooksResult == null) 0 else mBooksResult!!.size
  }

  /**
   * 傳入 及 更新資料
   * @param newResult
   * book 查詢結果
   */
  internal fun swapResult(newResult: List<Book>) {

    if (mBooksResult == null) {
      mBooksResult = newResult
      notifyDataSetChanged()
    } else {
      val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
          return mBooksResult!!.size
        }

        override fun getNewListSize(): Int {
          return newResult.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
          return mBooksResult!![oldItemPosition].bookName == newResult[newItemPosition].bookName
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

          return mBooksResult!![oldItemPosition].bookAuthor == newResult[newItemPosition].bookAuthor
        }
      })
      // 更新
      mBooksResult = newResult
      // 將變更部分寄送給adapter
      diffResult.dispatchUpdatesTo(this)
    }
  }

  inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var mBookName: TextView
    internal var mBookAuthor: TextView

    init {

      mBookName = itemView.findViewById(R.id.tv_book_name)
      mBookAuthor = itemView.findViewById(R.id.tv_book_author)
    }
  }
}
