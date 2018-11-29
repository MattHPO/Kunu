package com.taipei.yanghaobo.kunu;

import android.content.Context;
import android.support.annotation.NonNull;

import com.taipei.yanghaobo.kunu.data.KunuDatabase;
import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.network.GoogleNetWorkDataSoruce;
import com.taipei.yanghaobo.kunu.ui.API.BookListedVMFactory;
import com.taipei.yanghaobo.kunu.ui.detail.DogDetailVMFactory;
import com.taipei.yanghaobo.kunu.ui.edit.DogDetailEditVMFactory;
import com.taipei.yanghaobo.kunu.ui.list.DogCardVMFactory;
import com.taipei.yanghaobo.kunu.ui.search.SearchVMFactory;

public class InjectorUtils {

    public static KunuRepository provideKunuRepository(Context context){
        KunuDatabase kunuDb = KunuDatabase.getInstance(context.getApplicationContext());
        KunuExecutors executors = KunuExecutors.getInstance();
        GoogleNetWorkDataSoruce googleNetWorkDataSoruce =
                GoogleNetWorkDataSoruce.getInstance(context.getApplicationContext(), executors);
        return KunuRepository.getInstance(kunuDb.getDogDao(), executors, googleNetWorkDataSoruce);
    }

    @NonNull
    public static GoogleNetWorkDataSoruce provideGoogleNetWorkDataSoruce(Context context){
        // 在某些情況下如從 service 啟動 App ，Repo可能還不存在，預防
        provideKunuRepository(context);

        KunuExecutors kunuExecutors = KunuExecutors.getInstance();
        return GoogleNetWorkDataSoruce.getInstance(context.getApplicationContext(), kunuExecutors);
    }

    @NonNull
    public static DogCardVMFactory provideDogCardVMFactory(Context context){
        KunuRepository kunuRepository = provideKunuRepository(context.getApplicationContext());
        return new DogCardVMFactory(kunuRepository);
    }

    @NonNull
    public static DogDetailVMFactory provideDogDetailVMFactory(Context context, int id){
        KunuRepository kunuRepository = provideKunuRepository(context.getApplicationContext());
        return new DogDetailVMFactory(kunuRepository, id);
    }

    @NonNull
    public static SearchVMFactory provideSearchVMFactory(Context context){
        KunuRepository kunuRepository = provideKunuRepository(context.getApplicationContext());
        return new SearchVMFactory(kunuRepository);
    }

    @NonNull
    public static DogDetailEditVMFactory provideDogDetailEditVMFactory(Context context, int id){
        KunuRepository kunuRepository = provideKunuRepository(context.getApplicationContext());
        return new DogDetailEditVMFactory(kunuRepository, id);
    }

    @NonNull
    public static BookListedVMFactory provideBookListedVMFactory(Context context){
        KunuRepository kunuRepository = provideKunuRepository(context.getApplicationContext());
        return new BookListedVMFactory(kunuRepository);
    }

}
