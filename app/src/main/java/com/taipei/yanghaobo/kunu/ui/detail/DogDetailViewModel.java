package com.taipei.yanghaobo.kunu.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.db.DogEntry;

/**
 *  狗狗完整資料 VM
 */
public class DogDetailViewModel extends ViewModel {

    private LiveData<DogEntry> mDogEntryLiveData;

    /**
     * @param kunuRepository
     *          Repository
     * @param id
     *          要查詢的狗狗 id
     */
    public DogDetailViewModel(KunuRepository kunuRepository, int id) {
        mDogEntryLiveData = kunuRepository.getOneDog(id);
    }

    public LiveData<DogEntry> getOneDog() {
        return mDogEntryLiveData;
    }

}
