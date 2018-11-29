package com.taipei.yanghaobo.kunu.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.taipei.yanghaobo.kunu.KunuExecutors;
import com.taipei.yanghaobo.kunu.db.Book;
import com.taipei.yanghaobo.kunu.db.DogEntry;
import com.taipei.yanghaobo.kunu.db.DogListEntry;
import com.taipei.yanghaobo.kunu.db.DogDao;
import com.taipei.yanghaobo.kunu.network.GoogleNetWorkDataSoruce;

import java.util.List;

/**
 * 管理所有 Data source
 * 制定 資源API 給其他 component 使用
 */
public class KunuRepository {

    private static KunuRepository INSTANCE;
    private static final Object LOCK = new Object();
    private final KunuExecutors mKunuExecutors;
    private final DogDao mDogDao;
    private final GoogleNetWorkDataSoruce mGoogleNetWorkDataSoruce;

    private static final int sPageSize = 10;
    private static final int sInitialLoadSize = 10;

    private KunuRepository(DogDao dogDao, KunuExecutors executors, GoogleNetWorkDataSoruce googleNetWorkDataSoruce) {
        this.mDogDao = dogDao;
        this.mKunuExecutors = executors;
        this.mGoogleNetWorkDataSoruce = googleNetWorkDataSoruce;
    }

    public static KunuRepository getInstance(DogDao dogDao, KunuExecutors executors, GoogleNetWorkDataSoruce googleNetWorkDataSoruce) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new KunuRepository(dogDao, executors, googleNetWorkDataSoruce);
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
    @NonNull
    public LiveData<PagedList<DogEntry>> getDogAll() {

        DataSource.Factory factory = mDogDao.getAll();
        //noinspection unchecked
//        return (LiveData<PagedList<DogEntry>>) new LivePagedListBuilder(factory, sPageSize).build();

        //noinspection unchecked
        return (LiveData<PagedList<DogEntry>>) new LivePagedListBuilder(factory,
                new PagedList.Config.Builder()
                        .setPageSize(sPageSize)
                        .setInitialLoadSizeHint(sInitialLoadSize)
                        .setEnablePlaceholders(true)
                        .build())
                .build();
    }

    @NonNull
    public LiveData<PagedList<DogListEntry>> getAllDogList() {

        DataSource.Factory factory = mDogDao.getAllDogList();

        //noinspection unchecked
        return (LiveData<PagedList<DogListEntry>>) new LivePagedListBuilder(factory,
                new PagedList.Config.Builder()
                        .setPageSize(5)
                        .setInitialLoadSizeHint(10)
                        .setEnablePlaceholders(true)
                        .build())
                .build();
    }

    /**
     * 用 id 取得 單隻 狗狗資料
     *
     * @param id raw id
     * @return 符合的狗狗資料
     */
    public LiveData<DogEntry> getOneDog(int id) {
        return mDogDao.getOneDog(id);
    }

    /**
     * 用關鍵字搜尋狗狗
     *
     * @param keyWord 關鍵字
     * @return 搜尋結果
     */
    @NonNull
    public LiveData<PagedList<DogListEntry>> getDogByCNName(String keyWord) {
        DataSource.Factory<Integer, DogListEntry> factory = mDogDao.getByCNName(keyWord);

        //noinspection unchecked
        return (LiveData<PagedList<DogListEntry>>) new LivePagedListBuilder(factory,
                new PagedList.Config.Builder()
                        .setPageSize(5)
                        .setInitialLoadSizeHint(10)
                        .setEnablePlaceholders(true)
                        .build())
                .build();
    }

    /**
     * 新增狗狗資料
     *
     * @param dogEntry
     *           狗狗資料，可為多筆
     */
    public void addNewDog(@NonNull DogEntry... dogEntry){
        mKunuExecutors.getDiskIO().execute(() -> mDogDao.insert(dogEntry));
    }

    /**
     * 更新新狗狗資料
     *
     * @param dogEntry
     *           狗狗資料，可為多筆
     */
    public void updateDog(@NonNull DogEntry dogEntry){
        mKunuExecutors.getDiskIO().execute(() -> mDogDao.updateDog(dogEntry));
    }

    public LiveData<List<Book>> fetchBook(@NonNull String query){
        return mGoogleNetWorkDataSoruce.getBookList(query);
    }
}
