package com.example.a05_06_withcontext.model

import android.os.Parcel
import android.os.Parcelable

data class Music(val imageId:Int,val songName:String,val songId:Int,val lrcId:Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    )

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest?.writeInt(imageId)
        dest?.writeString(songName)
        dest?.writeInt(songId)
        dest?.writeInt(lrcId)
    }

    companion object CREATOR : Parcelable.Creator<Music> {
        override fun createFromParcel(parcel: Parcel?): Music {
            return Music(parcel!!)
        }

        override fun newArray(size: Int): Array<Music?> {
            return arrayOfNulls(size)
        }
    }
}