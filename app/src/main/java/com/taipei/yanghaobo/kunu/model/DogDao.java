package com.taipei.yanghaobo.kunu.model;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.taipei.yanghaobo.kunu.db.DogEntry;

@Dao
public interface DogDao {

    @Query("SELECT * FROM dog_table ORDER BY id ASC")
    DataSource.Factory<Integer, DogEntry> getAll();

    @Query("SELECT * FROM dog_table WHERE name_cn LIKE ('%' + :keyWord + '%') ")
    DataSource.Factory<Integer, DogEntry> getUseName(String keyWord);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DogEntry... dogEntries);

    @Delete
    void delete(DogEntry dog);

    @Query("DELETE FROM dog_table")
    void deleteAll();
}
