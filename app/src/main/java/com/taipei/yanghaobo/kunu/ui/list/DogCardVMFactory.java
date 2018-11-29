package com.taipei.yanghaobo.kunu.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.taipei.yanghaobo.kunu.data.KunuRepository;

public class DogCardVMFactory extends ViewModelProvider.NewInstanceFactory {

    private final KunuRepository mKunuRepository;

    public DogCardVMFactory(KunuRepository kunuRepository) {
        this.mKunuRepository = kunuRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new DogCardViewModel(mKunuRepository);
    }
}
