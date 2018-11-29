package com.taipei.yanghaobo.kunu.db;

import android.support.annotation.NonNull;

public class DogListEntry {

    /**
     * PK
     */
    private int id;

    /**
     * 中文名稱
     */
    @NonNull
    private String name_cn;

    /**
     * 照片id
     */
    private int photo_id;

    public DogListEntry(int id, @NonNull String name_cn, int photo_id) {
        this.id = id;
        this.name_cn = name_cn;
        this.photo_id = photo_id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName_cn() {
        return name_cn;
    }

    public int getPhoto_id() {
        return photo_id;
    }
}
