package com.taipei.yanghaobo.kunu.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.taipei.yanghaobo.kunu.db.DogEntry;
import com.taipei.yanghaobo.kunu.model.DogDao;

/**
 * 管理所有 Data source
 * 制定 API 給其他 component 使用
 */
public class KunuRepository {

    private static KunuRepository INSTANCE;
    private static final Object LOCK = new Object();

    private final DogDao mDogDao;

    private static final int sPageSize = 10;

    private KunuRepository(DogDao dogDao) {
        mDogDao = dogDao;
    }

    public static KunuRepository getInstance(DogDao dogDao) {

        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new KunuRepository(dogDao);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 取得所有狗狗資料
     *
     * @return 狗狗圖鑑全
     */
    public LiveData<PagedList<DogEntry>> getDogAll() {

        DataSource.Factory factory = mDogDao.getAll();
        //noinspection unchecked
        return (LiveData<PagedList<DogEntry>>) new LivePagedListBuilder(factory, sPageSize).build();
//        return  new LivePagedListBuilder(factory,
//                new PagedList.Config.Builder()
//                .setPageSize(10)
//                .setInitialLoadSizeHint(10)
//                .setEnablePlaceholders(true)
//                .build()).build();
    }
}
