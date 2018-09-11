package com.taipei.yanghaobo.kunu;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.db.DogEntry;

public class MainActivityViewModel extends ViewModel{

    private LiveData<PagedList<DogEntry>> mDogList;

    public MainActivityViewModel(KunuRepository kunuRepository) {

        mDogList = kunuRepository.getDogAll();
    }

    public LiveData<PagedList<DogEntry>> getDogList() {
        return mDogList;
    }
}
