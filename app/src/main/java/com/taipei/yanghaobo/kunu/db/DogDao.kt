package com.taipei.yanghaobo.kunu.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DogDao {

  /**
   * 取得所有狗狗資料
   *
   * @return
   */
  @get:Query(" SELECT * FROM dog_table ORDER BY id ASC ")
  val all: DataSource.Factory<Int, DogEntry>

  /**
   * 取得 列表清單 展示所需資料
   * : id, name_cn, photo_id
   *
   * @return
   * DogListEntry
   */
  @get:Query(" SELECT * FROM dog_table ORDER BY id ASC ")
  val allDogList: DataSource.Factory<Int, DogListEntry>

  /**
   * 使用中文關鍵字尋找狗狗
   *
   * @param keyWord
   * 中文姓名關鍵字
   * @return
   * 符合的狗狗清單
   */
  @Query(" SELECT * FROM dog_table WHERE name_cn LIKE '%' || :keyWord || '%' ")
  fun getByCNName(keyWord: String): DataSource.Factory<Int, DogListEntry>

  /**
   * 只找一隻狗狗
   *
   * @param id
   * rowId
   * @return
   * 一隻狗狗的資料
   */
  @Query(" SELECT * FROM dog_table WHERE id = :id ")
  fun getOneDog(id: Int): LiveData<DogEntry>

  /**
   * 新增 DogEntry
   *
   * @param dogEntries
   * 狗狗資料 - 多筆
   */
  @WorkerThread
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(vararg dogEntries: DogEntry)

  /**
   * 更新 DogEntry
   *
   * @param dogEntry
   * 狗狗資料 - 單筆
   */
  @WorkerThread
  @Update
  fun updateDog(dogEntry: DogEntry): Int

  /**
   * 刪除狗狗資料 （再見了...）
   *
   * @param dog
   * 要刪除的狗狗資料
   */
  @WorkerThread
  @Delete
  fun delete(dog: DogEntry)

  /**
   * 再見了Dog table !
   */
  @WorkerThread
  @Query(" DELETE FROM dog_table ")
  fun deleteAll()

  /**
   * 使用狗狗中文名稱，刪除狗狗資料
   *
   * @param name_cn
   * 要被刪除的可憐狗狗名
   */
  @WorkerThread
  @Query(" DELETE FROM dog_table WHERE name_cn = :name_cn ")
  fun deleteByCNName(name_cn: String)
}
