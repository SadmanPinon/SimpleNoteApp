package com.example.a4_notes

import android.view.View
import android.widget.ListView
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class MainActivityViewModel(
    private var dataList: ArrayList<NoteData> = ArrayList(),
):ViewModel() {
    var classListView: ListView? = null
    var thisContext: MainActivity? = null
    var thisAdapter : NotesAdapter? = null

    companion object {


    }
    fun displayList(listView: ListView = classListView!!, context: MainActivity = thisContext!!) {
        if (classListView==null) classListView = listView
        if (thisContext==null) thisContext = context

//        dataList.add(NoteData("Test","This is just a test"))
//        dataList.add(NoteData("Test1","This is just a test"))
//        dataList.add(NoteData("Test2","This is just a test"))
//        dataList.add(NoteData("Test3","This is just a test"))
//        dataList.add(NoteData("Test4","This is just a test"))
//        dataList.add(NoteData("Test5","This is just a test"))
//        dataList.add(NoteData("Test6","This is just a test"))
//        dataList.add(NoteData("Test7","This is just a test"))
//        dataList.add(NoteData("Test8","This is just a test"))
//        dataList.add(NoteData("Test9","This is just a test"))
//        dataList.add(NoteData("Test0","This is just a test"))
        if (thisAdapter==null)
            thisAdapter = NotesAdapter(context, dataList,this)



        listView.adapter = thisAdapter
    }



    fun randomNote(){
        dataList.add(NoteData("Random","Random Body Just testing things out"))
        thisAdapter!!.notifyDataSetChanged()
    }


}