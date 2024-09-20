package com.example.mytodolist

import android.os.Parcel
import android.os.Parcelable


class Note(var number: Int, var date: String, var noteText: String, var isDone: Boolean = false):
        Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString().toString(),
                parcel.readString().toString(),
                parcel.readByte() != 0.toByte()
        )


        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(number)
                parcel.writeString(date)
                parcel.writeString(noteText)
                parcel.writeByte(if (isDone) 1 else 0)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Note> {
                override fun createFromParcel(parcel: Parcel): Note {
                        return Note(parcel)
                }

                override fun newArray(size: Int): Array<Note?> {
                        return arrayOfNulls(size)
                }
        }
}
