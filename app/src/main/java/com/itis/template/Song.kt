package com.itis.template

import android.os.Parcel
import android.os.Parcelable

class Song() : Parcelable {

    var name: String = ""

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song> {
            return Array(size) { Song() }
        }
    }

    private constructor(inParcel: Parcel) : this() {
        readFromParcel(inParcel)
    }

    private fun readFromParcel(inParcel: Parcel) {
        name = inParcel.readString() ?: ""
    }

    override fun writeToParcel(outParcel: Parcel, flags: Int) {
        outParcel.writeString(name)
    }

    override fun describeContents(): Int = 0
}
