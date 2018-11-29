package com.taipei.yanghaobo.kunu.data;

import android.arch.persistence.room.TypeConverter;
import android.support.annotation.Nullable;

import java.util.Date;


public class DataConverters {

    @Nullable
    @TypeConverter
    public static Date toDate(@Nullable Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @Nullable
    @TypeConverter
    public static Long toTimestamp(@Nullable Date date) {
        return date == null ? null : date.getTime();
    }
}
