package com.example.mytodolist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class FirstFragment : Fragment() {
    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private var adapter: CustomAdapter? = null
    private var noteList = arrayListOf<Note>()
    private var note: Note? = null
    private lateinit var noteET: EditText
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("NewApi", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        val view = inflater.inflate(R.layout.fragment_first, container, false)


            noteET = view.findViewById(R.id.noteET)
            val saveBT = view.findViewById<Button>(R.id.saveBT)
            recyclerView = view.findViewById(R.id.recyclerView)
            view.findViewById<Toolbar>(R.id.toolbar).apply {
                setTitle("Мои заметки")
                setNavigationIcon(R.drawable.ic_exit)
                setNavigationOnClickListener {
                    requireActivity().finish()
                }
            }

        val newList = arguments?.getParcelableArrayList<Note>("newList")
        if (newList != null) {
            noteList = newList
        }
        val newPosition = arguments?.getInt("positionNew")
        if (newPosition != null) {
            createAdapter()
        }



        val dateNow = LocalDate.now()
        val formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val formatDayNow = dateNow.format(formatDate)

        saveBT.setOnClickListener {
            val number = noteList.size + 1
            val text = noteET.text.toString()
            note = Note(number, formatDayNow, text, false)
            noteList.add(note!!)
            noteET.text.clear()
            createAdapter()
        }

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun createAdapter() {
        adapter = CustomAdapter(noteList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter?.notifyDataSetChanged()
        adapter?.setOnNoteClickListener(object : CustomAdapter.OnNoteClickListener {
            override fun onNoteClick(note: Note, position: Int) {
                onFragmentDataListener.onData(noteList, position)
            }
        })
    }
}