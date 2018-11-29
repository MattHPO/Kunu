package com.taipei.yanghaobo.kunu.ui.search;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.taipei.yanghaobo.kunu.data.KunuRepository;
import com.taipei.yanghaobo.kunu.db.DogListEntry;

public class SearchViewModel extends ViewModel {

    @NonNull
    private MutableLiveData<String> mQueryText = new MutableLiveData<>();
    private LiveData<PagedList<DogListEntry>> mQueryResultList;

    public SearchViewModel(@NonNull KunuRepository kunuRepository) {

        mQueryResultList = Transformations.switchMap(mQueryText, new Function<String, LiveData<PagedList<DogListEntry>>>() {
            @Override
            public LiveData<PagedList<DogListEntry>> apply(String input) {
                return kunuRepository.getDogByCNName(input);
            }
        });
    }

    /**
     * 查詢
     *
     * @param queryText
     *          查詢關鍵字
     */
    public void queryDog(String queryText){
        mQueryText.setValue(queryText);
    }

    /**
     * 取得查詢結果
     * @return
     *         查詢結果列表
     */
    public LiveData<PagedList<DogListEntry>> getQueryResultList(){
        return mQueryResultList;
    }
}
