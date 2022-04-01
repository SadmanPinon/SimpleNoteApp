package com.example.a4_notes

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class NotesAdapter(
    private val context: Context,
    private val dataSource: ArrayList<NoteData>,

) :BaseAdapter (){
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val noteView = inflater.inflate(R.layout.layout_note, parent, false)

        val titleTextView = noteView.findViewById(R.id.tv_titleText) as TextView
        val bodyTextView = noteView.findViewById(R.id.tv_bodyText) as TextView
        val deleteButton = noteView.findViewById(R.id.deleteButton) as Button
        val note = noteView.findViewById(R.id.note) as LinearLayout
        val noteData = getItem(position) as NoteData

        if (noteData.isImportant) note.setBackgroundColor(Color.parseColor("#FFB6C1"))
        noteView.id= position

        titleTextView.text = noteData.title
        bodyTextView.text = noteData.body
        deleteButton.id = position



        deleteButton.setOnClickListener{
            dataSource.removeAt(position)
            MainActivityViewModel.callSnackbar("Deleted Note# $position")

//            MainActivityViewModel.thisContext!!.makeSnackbar("Deleted Note#${position}")
            notifyDataSetChanged()
        }

        noteView.findViewById<LinearLayout>(R.id.clickingZone).setOnClickListener{
            NoteData.selectedNote = dataSource[position]
            ListFragment.navigate(noteData,position)
        }

        return noteView
    }





}