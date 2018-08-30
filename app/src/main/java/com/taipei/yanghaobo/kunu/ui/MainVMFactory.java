package com.taipei.yanghaobo.kunu.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.taipei.yanghaobo.kunu.data.KunuRepository;

public class MainVMFactory extends ViewModelProvider.NewInstanceFactory {

    private final KunuRepository mKunuRepository;

    public MainVMFactory(KunuRepository kunuRepository) {
        this.mKunuRepository = kunuRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityVM(mKunuRepository);
    }
}
