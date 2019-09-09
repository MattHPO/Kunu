package com.taipei.yanghaobo.kunu.data

import androidx.room.TypeConverter

import java.util.Date

object DataConverters {

  @TypeConverter
  fun toDate(timestamp: Long?): Date? {
    return if (timestamp == null) null else Date(timestamp)
  }

  @TypeConverter
  fun toTimestamp(date: Date?): Long? {
    return date?.time
  }
}
