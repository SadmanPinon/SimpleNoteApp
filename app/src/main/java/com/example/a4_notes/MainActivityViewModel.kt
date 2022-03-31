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

):ViewModel() {



    companion object {
        private var dataList: ArrayList<NoteData> = ArrayList()
        var addButtonCalled:Boolean = true
        var recentUpdate:String = "Welcome!"
        var classListView: ListView? = null
        var thisContext: MainActivity? = null
        var thisAdapter : NotesAdapter? = null
        var clearButton : Button? = null


        fun displayList(listView: ListView = classListView!!, context: MainActivity = thisContext!!) {
//        if (classListView==null) classListView = listView
//        if (thisContext==null) {
//            thisContext = context
//            clearButton = thisContext!!.findViewById<Button>(R.id.clearButton)
//
//        }


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
                thisAdapter = NotesAdapter(context, dataList)

            listView.adapter = thisAdapter



        }



        fun randomNote(){
            val isImportant = Math.random() >= 1.0 - 0.2
            dataList.add(NoteData("Random","Random Body Just testing things out",isImportant))

            if (dataList.size!=0)
                clearButton!!.isEnabled = true

//            callSnackbar(message = "Added Note #${dataList.size-1}")
            thisAdapter!!.notifyDataSetChanged()

        }

        fun clearNotes() {
            dataList.clear()
//            callSnackbar(message = "Cleared all notes")
            thisAdapter!!.notifyDataSetChanged()
        }

//        fun callSnackbar( message: String= recentUpdate) {
//            if (recentUpdate== "") return
//
//            val listPage = thisContext!!.findViewById<ListView>(R.id.noteView)
//            Snackbar.make(
//                listPage,
//                message,
//                Snackbar.LENGTH_LONG
//            )
//                .setAnchorView(thisContext!!.findViewById<LinearLayout>(R.id.linearLayout3))
//                .show()
//            if (dataList.size==0)
//                clearButton!!.isEnabled = false
//
//            recentUpdate = ""
//
//
//        }

        fun addNewNote(title:String,body:String,isImportant:Boolean){
            dataList.add(NoteData(title,body,isImportant))
            thisAdapter!!.notifyDataSetChanged()
            recentUpdate = "Added Note #${dataList.size-1}"
        }



    }





}