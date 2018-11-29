package com.taipei.yanghaobo.kunu.ui.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.taipei.yanghaobo.kunu.data.KunuRepository;

public class DogDetailVMFactory extends ViewModelProvider.NewInstanceFactory {

    private KunuRepository mKunuRepository;
    private int mId;

    public DogDetailVMFactory(KunuRepository kunuRepository, int id) {
        this.mKunuRepository = kunuRepository;
        this.mId = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DogDetailViewModel(mKunuRepository, mId);
    }
}
