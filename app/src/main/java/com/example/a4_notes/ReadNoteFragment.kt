package com.example.a4_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.chip.Chip



/**
 * Fragment for note viewing
 */
class ReadNoteFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_read_note, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Get views for data to be updated from note data
        var title = view.findViewById<TextView>(R.id.t_noteTitleText)
        var body = view.findViewById<TextView>(R.id.t_noteBodyText)
        var isImporantChip = view.findViewById<Chip>(R.id.t_isImportantChip)
        var header = view.findViewById<TextView>(R.id.t_noteNumberText)
        var editButton = view.findViewById<Button>(R.id.t_noteEditButton)


        //If note data provided, fetch and update them to the views
        arguments!!.apply {
            title.text = getString("title")
            body.text = getString("body")
            if (!getBoolean("isImportant"))
            { isImporantChip.visibility = View.INVISIBLE}
            else {isImporantChip.visibility = View.VISIBLE}

            header.text = "Note #${getInt("position")}"
        }

        //If Edit request made, package data to next fragment and navigate to next fragment
        editButton.setOnClickListener{
            Navigation.findNavController(view).navigate(
                R.id.action_readNoteFragment_to_updateFragment,
                args = Bundle().apply {
                    putString("title",arguments!!.getString("title"))
                    putString("body",arguments!!.getString("body"))
                    putBoolean("isImportant",arguments!!.getBoolean("isImportant"))
                    putInt("position",arguments!!.getInt("position"))
                    putString("buttonName","Update")
                }

            )
        }



    }



}