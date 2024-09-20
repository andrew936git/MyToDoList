package com.example.mytodolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val list: ArrayList<Note>):
    RecyclerView.Adapter<CustomAdapter.NoteViewHolder>() {

    private var onNoteClickListener: OnNoteClickListener? = null

    interface OnNoteClickListener{
        fun onNoteClick(note: Note, position: Int)
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val numberNoteTV: TextView = itemView.findViewById(R.id.numberNoteTV)
        val dateNoteTV: TextView = itemView.findViewById(R.id.dateNoteTV)
        val textNoteTV: TextView = itemView.findViewById(R.id.textNoteTV)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = list[position]
        holder.numberNoteTV.text = note.number.toString()
        holder.dateNoteTV.text = note.date
        holder.textNoteTV.text = note.noteText
        holder.checkBox.isChecked = note.isDone
        holder.itemView.setOnClickListener {
            if (onNoteClickListener != null) {
                onNoteClickListener?.onNoteClick(note, position)
            }
        }

    }
    fun setOnNoteClickListener(onNoteClickListener: OnNoteClickListener){
        this.onNoteClickListener = onNoteClickListener
    }
}