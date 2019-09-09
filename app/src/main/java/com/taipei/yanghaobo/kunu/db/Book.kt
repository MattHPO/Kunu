package com.taipei.yanghaobo.kunu.db

import com.google.gson.annotations.SerializedName

/**
 * Google book object
 */
class Book(
  @field:SerializedName("title")
  val bookName: String, @field:SerializedName("authors")
  val bookAuthor: String
) {

  override fun toString(): String {
    return "Book{" +
        "mBookName='" + bookName + '\''.toString() +
        ", mBookAuthor='" + bookAuthor + '\''.toString() +
        '}'.toString()
  }
}
