package com.example.a4_notes

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.FieldPosition

class MainActivityViewModel(
    private var dataList: ArrayList<NoteData> = ArrayList(),
):ViewModel() {
    var classListView: ListView? = null
    var thisContext: MainActivity? = null
    var thisAdapter : NotesAdapter? = null
    var clearButton : Button? = null


    fun displayList(listView: ListView = classListView!!, context: MainActivity = thisContext!!) {
        if (classListView==null) classListView = listView
        if (thisContext==null) {
            thisContext = context
            clearButton = thisContext!!.findViewById<Button>(R.id.clearButton)
        }

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

        if (dataList.size!=0)
            clearButton!!.isEnabled = true

        callSnackbar(action = "Added")
        thisAdapter!!.notifyDataSetChanged()

    }

    fun clearNotes() {
        dataList.clear()
        callSnackbar(action = "cleared")
        thisAdapter!!.notifyDataSetChanged()
    }

    fun callSnackbar(index: Int=0, action: String) {
        var message:String
        message = if (action=="cleared") "Cleared all Notes";
        else "${action} Note#${index}"

        val listPage = thisContext!!.findViewById<ListView>(R.id.rvNotesView)
        Snackbar.make(
            listPage,
            message,
            Snackbar.LENGTH_LONG
        )
            .setAnchorView(thisContext!!.findViewById<LinearLayout>(R.id.linearLayout3))
            .show()
        if (dataList.size==0)
            clearButton!!.isEnabled = false


    }


}