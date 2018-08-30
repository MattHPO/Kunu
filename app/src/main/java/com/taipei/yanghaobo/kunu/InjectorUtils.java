package com.taipei.yanghaobo.kunu;

import android.content.Context;

import com.taipei.yanghaobo.kunu.data.KunuDatabase;
import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.ui.MainVMFactory;

public class InjectorUtils {

    public static KunuRepository provideKunuRepository(Context context){
        KunuDatabase kunuDb = KunuDatabase.getInstance(context.getApplicationContext());
        return KunuRepository.getInstance(kunuDb.DogDao());
    }

    public static MainVMFactory provideMainVMFactory(Context context){
        KunuRepository kunuRepository = provideKunuRepository(context.getApplicationContext());
        return new MainVMFactory(kunuRepository);
    }
}
