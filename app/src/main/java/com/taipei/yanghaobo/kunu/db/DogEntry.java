package com.taipei.yanghaobo.kunu.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.taipei.yanghaobo.kunu.data.DataConverters;

import java.util.Date;

@TypeConverters(DataConverters.class)
@Entity(tableName = "dog_table")
public class DogEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * 中文名稱
     */
    private String name_cn;

    /**
     * 英文名稱
     */
    private String name_en;

    /**
     * 體型
     */
    private String type;

    /**
     * 狗狗資料
     */
    private String info;

    /**
     * 照片id
     */
    private int photo_id;

    /**
     * 狗狗資料更新日期
     */
    private Date update_dt;

    /**
     * 是否有世界狗協會認證的品種
     */
    private boolean is_valid;

    @Ignore
    public DogEntry(String name_cn, String name_en, String type, String info, int photo_id, Date update_dt, boolean is_valid) {
        this.name_cn = name_cn;
        this.name_en = name_en;
        this.type = type;
        this.info = info;
        this.photo_id = photo_id;
        this.update_dt = update_dt;
        this.is_valid = is_valid;
    }

    public DogEntry(int id, String name_cn, String name_en, String type, String info, int photo_id, Date update_dt, boolean is_valid) {
        this.id = id;
        this.name_cn = name_cn;
        this.name_en = name_en;
        this.type = type;
        this.info = info;
        this.photo_id = photo_id;
        this.update_dt = update_dt;
        this.is_valid = is_valid;
    }

    /** getters */
    public int getId() {
        return id;
    }

    public String getName_cn() {
        return name_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public Date getUpdate_dt() {
        return update_dt;
    }

    public boolean isIs_valid() {
        return is_valid;
    }
}
