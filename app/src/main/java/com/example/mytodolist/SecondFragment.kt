package com.example.mytodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class SecondFragment : Fragment() {
    private lateinit var detailsET: EditText
    private lateinit var editBT: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_second, container, false)
        val note = arguments?.getSerializable("note") as Note
        val position = arguments?.getInt("position")
        detailsET = view.findViewById(R.id.detailsET)
        editBT = view.findViewById(R.id.editBT)
        editBT.text = note.noteText

        return view
    }
}