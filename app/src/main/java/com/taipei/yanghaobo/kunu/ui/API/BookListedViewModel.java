package com.taipei.yanghaobo.kunu.ui.API;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.db.Book;

import java.util.List;

public class BookListedViewModel extends ViewModel {

    private MutableLiveData<String> mBookQueryText = new MutableLiveData<>();
    private LiveData<List<Book>> mBookQueryResult;

    public BookListedViewModel(KunuRepository kunuRepository) {

        mBookQueryResult = Transformations.switchMap(mBookQueryText, input -> kunuRepository.fetchBook(input));
    }

    public void queryBookListed(String query) {
        mBookQueryText.setValue(query);
    }

    public LiveData<List<Book>> getBookQueryResult() {
        return mBookQueryResult;
    }
}
