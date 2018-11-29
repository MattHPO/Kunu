package com.taipei.yanghaobo.kunu.ui.edit;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.taipei.yanghaobo.kunu.data.KunuRepository;

public class DogDetailEditVMFactory extends ViewModelProvider.NewInstanceFactory {

    private final KunuRepository mKunuRepository;
    private final int mId;

    public DogDetailEditVMFactory(KunuRepository kunuRepository, int id) {
        this.mKunuRepository = kunuRepository;
        this.mId = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) (new DogDetailEditViewModel(mKunuRepository, mId));
    }
}
