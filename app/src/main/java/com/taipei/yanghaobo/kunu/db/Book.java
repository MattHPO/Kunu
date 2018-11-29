package com.taipei.yanghaobo.kunu.db;

/**
 * Google book object
 */
public class Book {
    private String mBookName;

    private String mBookAuthor;

    public Book(String bookName, String bookAuthor) {
        mBookName = bookName;
        mBookAuthor = bookAuthor;
    }

    public String getBookName() {
        return mBookName;
    }

    public String getBookAuthor() {
        return mBookAuthor;
    }
}
