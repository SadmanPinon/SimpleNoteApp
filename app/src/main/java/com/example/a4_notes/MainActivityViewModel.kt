package com.example.a4_notes

import android.widget.ListView
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    fun displayList(listView: ListView, context: MainActivity) {
        var dataList = ArrayList<NoteData>()
        dataList.add(NoteData("Test","This is just a test"))
        dataList.add(NoteData("Test1","This is just a test"))
        dataList.add(NoteData("Test2","This is just a test"))
        dataList.add(NoteData("Test3","This is just a test"))
        dataList.add(NoteData("Test4","This is just a test"))
        dataList.add(NoteData("Test5","This is just a test"))
        dataList.add(NoteData("Test6","This is just a test"))
        dataList.add(NoteData("Test7","This is just a test"))
        dataList.add(NoteData("Test8","This is just a test"))
        dataList.add(NoteData("Test9","This is just a test"))
        dataList.add(NoteData("Test0","This is just a test"))

        val adapter = NotesAdapter(context, dataList)
        listView.adapter = adapter
    }

    init {

    }
}