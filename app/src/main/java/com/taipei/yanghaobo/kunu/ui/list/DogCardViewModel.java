package com.taipei.yanghaobo.kunu.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.db.DogListEntry;

/**
 * 狗狗列表所需資訊 VM
 * (僅有 id, name_cn, photo_id)
 */
public class DogCardViewModel extends ViewModel {

    private LiveData<PagedList<DogListEntry>> mDogList;

    public DogCardViewModel(KunuRepository kunuRepository) {

        mDogList = kunuRepository.getAllDogList();
    }

    public LiveData<PagedList<DogListEntry>> getAllDogList() {
        return mDogList;
    }
}
