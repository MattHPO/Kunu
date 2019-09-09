package com.taipei.yanghaobo.kunu.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

import com.taipei.yanghaobo.kunu.data.DataConverters

import java.util.Date

@TypeConverters(DataConverters::class)
@Entity(tableName = "dog_table")
class DogEntry : Parcelable {
  // , indices = {@Index(value = {"date"}, unique = true)}
  /** getters  */
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
    private set

  /**
   * 中文名稱
   */
  var name_cn: String

  /**
   * 英文名稱
   */
  var name_en: String? = null

  /**
   * 其他名稱
   */
  var other_names: String? = null

  /**
   * 小名、綽號
   */
  var nicknames: String? = null

  /**
   * 產地
   */
  var origin: String? = null

  /**
   * 體型
   */
  var type: String? = null

  /**
   * 狗狗資料
   */
  var info: String? = null

  /**
   * 照片id
   */
  var photo_id: Int = 0

  /**
   * 狗狗資料更新日期
   */
  var update_dt: Date? = null

  /**
   * 是否有世界狗協會認證的品種
   */
  var isIs_valid: Boolean = false

  constructor(
    id: Int,
    name_cn: String,
    name_en: String,
    other_names: String,
    nicknames: String,
    origin: String,
    type: String,
    info: String,
    photo_id: Int,
    update_dt: Date,
    is_valid: Boolean
  ) {
    this.id = id
    this.name_cn = name_cn
    this.name_en = name_en
    this.other_names = other_names
    this.nicknames = nicknames
    this.origin = origin
    this.type = type
    this.info = info
    this.photo_id = photo_id
    this.update_dt = update_dt
    this.isIs_valid = is_valid
  }

  @Ignore
  constructor(
    name_cn: String,
    name_en: String,
    other_names: String,
    nicknames: String,
    origin: String,
    type: String,
    info: String,
    photo_id: Int,
    update_dt: Date,
    is_valid: Boolean
  ) {
    this.name_cn = name_cn
    this.name_en = name_en
    this.other_names = other_names
    this.nicknames = nicknames
    this.origin = origin
    this.type = type
    this.info = info
    this.photo_id = photo_id
    this.update_dt = update_dt
    this.isIs_valid = is_valid
  }

  @Ignore
  constructor() {
  }

  override fun describeContents(): Int {
    return 0
  }

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeInt(this.id)
    dest.writeString(this.name_cn)
    dest.writeString(this.name_en)
    dest.writeString(this.other_names)
    dest.writeString(this.nicknames)
    dest.writeString(this.origin)
    dest.writeString(this.type)
    dest.writeString(this.info)
    dest.writeInt(this.photo_id)
    dest.writeLong(if (this.update_dt != null) this.update_dt!!.time else -1)
    dest.writeByte(if (this.isIs_valid) 1.toByte() else 0.toByte())
  }

  protected constructor(`in`: Parcel) {
    this.id = `in`.readInt()
    this.name_cn = `in`.readString()
    this.name_en = `in`.readString()
    this.other_names = `in`.readString()
    this.nicknames = `in`.readString()
    this.origin = `in`.readString()
    this.type = `in`.readString()
    this.info = `in`.readString()
    this.photo_id = `in`.readInt()
    val tmpUpdate_dt = `in`.readLong()
    this.update_dt = if (tmpUpdate_dt == -1) null else Date(tmpUpdate_dt)
    this.isIs_valid = `in`.readByte().toInt() != 0
  }

  override fun equals(o: Any?): Boolean {
    if (this === o) return true
    if (o == null || javaClass != o.javaClass) return false

    val dogEntry = o as DogEntry?

    return if (id != dogEntry!!.id) false else name_cn == dogEntry.name_cn
  }

  override fun hashCode(): Int {
    var result = id
    result = 31 * result + name_cn.hashCode()
    return result
  }

  companion object {

    val CREATOR: Parcelable.Creator<DogEntry> = object : Parcelable.Creator<DogEntry> {
      override fun createFromParcel(source: Parcel): DogEntry {
        return DogEntry(source)
      }

      override fun newArray(size: Int): Array<DogEntry> {
        return arrayOfNulls(size)
      }
    }
  }

  companion object CREATOR : Creator<DogEntry> {
    override fun createFromParcel(parcel: Parcel): DogEntry {
      return DogEntry(parcel)
    }

    override fun newArray(size: Int): Array<DogEntry?> {
      return arrayOfNulls(size)
    }
  }
}
