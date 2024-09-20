package com.example.mytodolist

interface OnFragmentDataListener {
    fun onData(notes: ArrayList<Note>, position: Int)
}