package com.example.mytodolist

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Note(var number: Int, var date: String, var noteText: String, var isDone: Boolean = false):
        Serializable
