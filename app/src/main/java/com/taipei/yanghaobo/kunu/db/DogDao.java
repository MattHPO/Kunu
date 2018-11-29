package com.taipei.yanghaobo.kunu.db;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.WorkerThread;

@Dao
public interface DogDao {

    /**
     * 取得所有狗狗資料
     *
     * @return
     */
    @Query(" SELECT * FROM dog_table ORDER BY id ASC ")
    DataSource.Factory<Integer, DogEntry> getAll();

    /**
     * 取得 列表清單 展示所需資料
     * : id, name_cn, photo_id
     *
     * @return
     *      DogListEntry
     */
    @Query(" SELECT * FROM dog_table ORDER BY id ASC ")
    DataSource.Factory<Integer, DogListEntry> getAllDogList();

    /**
     * 使用中文關鍵字尋找狗狗
     *
     * @param keyWord
     *          中文姓名關鍵字
     * @return
     *          符合的狗狗清單
     */
    @Query(" SELECT * FROM dog_table WHERE name_cn LIKE '%' || :keyWord || '%' ")
    DataSource.Factory<Integer, DogListEntry> getByCNName(String keyWord);

    /**
     * 只找一隻狗狗
     *
     * @param id
     *          rowId
     * @return
     *          一隻狗狗的資料
     */
    @Query(" SELECT * FROM dog_table WHERE id = :id ")
    LiveData<DogEntry> getOneDog(int id);

    /**
     * 新增 DogEntry
     *
     * @param dogEntries
     *          狗狗資料 - 多筆
     */
    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DogEntry... dogEntries);

    /**
     * 更新 DogEntry
     *
     * @param dogEntry
     *          狗狗資料 - 單筆
     */
    @WorkerThread
    @Update
    int updateDog(DogEntry dogEntry);

    /**
     * 刪除狗狗資料 （再見了...）
     *
     * @param dog
     *          要刪除的狗狗資料
     */
    @WorkerThread
    @Delete
    void delete(DogEntry dog);

    /**
     * 再見了Dog table !
     */
    @WorkerThread
    @Query(" DELETE FROM dog_table ")
    void deleteAll();

    /**
     * 使用狗狗中文名稱，刪除狗狗資料
     *
     * @param name_cn
     *          要被刪除的可憐狗狗名
     */
    @WorkerThread
    @Query(" DELETE FROM dog_table WHERE name_cn = :name_cn ")
    void deleteByCNName(String name_cn);
}
