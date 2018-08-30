package com.taipei.yanghaobo.kunu.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.db.DogEntry;

public class MainActivityVM extends ViewModel{

    private LiveData<PagedList<DogEntry>> mDogList;

    public MainActivityVM(KunuRepository kunuRepository) {

        mDogList = kunuRepository.getDogAll();
    }

    public LiveData<PagedList<DogEntry>> getDogList() {
        return mDogList;
    }
}
