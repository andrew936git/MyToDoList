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
    private var noteList = mutableListOf<Note>()

    @SuppressLint("NewApi", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val noteET = view.findViewById<EditText>(R.id.noteET)
        val saveBT = view.findViewById<Button>(R.id.saveBT)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar).apply{
            setTitle("Мои заметки")
            setNavigationIcon(R.drawable.ic_exit)
            setNavigationOnClickListener {
                requireActivity().finish()
            }
        }
        val dateNow = LocalDate.now()
        val formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val formatDayNow = dateNow.format(formatDate)

        saveBT.setOnClickListener {
            val number = noteList.size + 1
            val text = noteET.text.toString()

            noteList.add(Note(number, formatDayNow, text, false))
            adapter = context?.let { CustomAdapter(noteList) }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
            adapter!!.notifyDataSetChanged()
            noteET.text.clear()
        }
        adapter?.setOnNoteClickListener(object :
        CustomAdapter.OnNoteClickListener{

                override fun onNoteClick(note: Note, position: Int) {

                    onFragmentDataListener.onData(position, noteList[position])
                }
            }
        )
        return view
    }

}