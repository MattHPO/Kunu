package com.taipei.yanghaobo.kunu.ui.edit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.db.DogEntry;

public class DogDetailEditViewModel extends ViewModel {

    private KunuRepository mKunuRepository;
    private LiveData<DogEntry> mDogEntry;

    public DogDetailEditViewModel(KunuRepository kunuRepository, int id){

        this.mKunuRepository = kunuRepository;
        this.mDogEntry = kunuRepository.getOneDog(id);
    }

    /**
     * 取得狗狗資料
     *
     * @return
     *      狗狗詳細資料
     */
    public LiveData<DogEntry> getDogEntry() {
        return mDogEntry;
    }

    /**
     * 更新狗狗資料
     *
     * @param dogEntry
     *         被更新的資料
     */
    public void updateDogDetail(DogEntry dogEntry){
        mKunuRepository.updateDog(dogEntry);
    }
}
