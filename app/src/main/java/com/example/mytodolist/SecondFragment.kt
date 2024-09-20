package com.example.mytodolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction


class SecondFragment : Fragment(), OnFragmentDataListener {
    private lateinit var onFragmentDataListener: OnFragmentDataListener
    private lateinit var detailsET: EditText
    private lateinit var editBT: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onFragmentDataListener = requireActivity() as OnFragmentDataListener
        val view =  inflater.inflate(R.layout.fragment_second, container, false)
        val notes = arguments?.getParcelableArrayList<Note>("notes")
        val position = arguments?.getInt("position")
        detailsET = view.findViewById(R.id.detailsET)
        editBT = view.findViewById(R.id.editBT)
        detailsET.setText(notes!![position!!].noteText)

        editBT.setOnClickListener{
            notes[position].noteText = detailsET.text.toString()
                onData(notes, position)

        }

        return view
    }

    override fun onData(notes: ArrayList<Note>, position: Int) {
        val bundle = Bundle()
        bundle.putParcelableArrayList("newList", notes)
        bundle.putInt("positionNew", position)

        val transaction = fragmentManager?.beginTransaction()
        val firstFragment = FirstFragment()
        firstFragment.arguments = bundle

        transaction?.replace(R.id.fragment_container, firstFragment)
        transaction?.addToBackStack(null)
        transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction?.commit()
    }
}