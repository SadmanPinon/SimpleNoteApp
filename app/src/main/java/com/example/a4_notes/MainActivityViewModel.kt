package com.example.a4_notes

import android.view.View
import android.view.ViewGroup
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
        var HomeFragmentView: View? = null
        private var dataList: ArrayList<NoteData> = ArrayList()

        var addButtonCalled:Boolean = true
        var recentUpdate:String = "Welcome!"
        var classListView: ListView? = null
        var thisContext: MainActivity? = null
        var thisAdapter : NotesAdapter? = null
        var clearButton : Button? = null
        var currentSearchState: String = ""
        var currentIsImportantState: Boolean = false



        fun displayList(target: String= currentSearchState, isImportant: Boolean= currentIsImportantState, listView: ListView = classListView!!, context: MainActivity = thisContext!!) {
            currentSearchState = target
            currentIsImportantState = isImportant

            var filteredList = filter(target,isImportant)
            thisAdapter = NotesAdapter(context, filteredList)
//            if (thisAdapter==null)
//                thisAdapter = NotesAdapter(context, filteredList)

            listView.adapter = thisAdapter
            clearButton!!.isEnabled = filteredList.size!=0
            println("Target is $target")


        }



        fun randomNote(){
            val isImportant = Math.random() >= 1.0 - 0.2
            dataList.add(NoteData("Random","Random Body Just testing things out",isImportant))

            if (dataList.size!=0)
                clearButton!!.isEnabled = true

//            callSnackbar(message = "Added Note #${dataList.size-1}")
            displayList()
            thisAdapter!!.notifyDataSetChanged()

        }

        fun clearNotes() {
            dataList.clear()
//            callSnackbar(message = "Cleared all notes")
            if (dataList.size==0)
                clearButton!!.isEnabled = false
            thisAdapter!!.notifyDataSetChanged()
            displayList()
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

        fun replaceNote(title:String,body:String,isImportant:Boolean) {
            dataList.remove(NoteData.selectedNote)
            addNewNote(title,body,isImportant)

        }

        fun filter(target:String,isImportant: Boolean): ArrayList<NoteData> {

            var filteredList = ArrayList<NoteData>()
            for (note in dataList){
                if (isImportant){
                    if (!note.isImportant) continue
                }
                if ((note.title.lowercase().contains(target.lowercase())) || (note.body.lowercase().contains(target.lowercase())))
                    filteredList.add(note)
            }
            println("Size is $filteredList")
            return filteredList
        }




    }





}