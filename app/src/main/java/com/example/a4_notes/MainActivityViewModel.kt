package com.example.a4_notes

import RandomInfo
import android.os.Message
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

        private var dataList: ArrayList<NoteData> = ArrayList() //Data Source



        var HomeFragmentView: View? = null //ViewModel tracking active fragment
        var classListView: ListView? = null //Generating Note Views
        var thisContext: MainActivity? = null //Main activity context
        var thisAdapter : NotesAdapter? = null //Custom adapter for adapting data into notes
        var clearButton : Button? = null //Instance of clearButton to enable/disable
        var addButtonCalled : Boolean = false //Flag for if updateScreen is called via addNote or viewNote route

        //Search parameters
        var currentSearchState: String = ""
        var currentIsImportantState: Boolean = false


        /**
         * Refreshes The Notes on display using data source
         * @param target Keyword in searchbar
         *  @param isImportant if filter is on
         *  @param listView The note views of lists
         *  @param context Activity which is to be refreshed
         *
         * **/
        fun displayList(target: String= currentSearchState, isImportant: Boolean= currentIsImportantState, listView: ListView = classListView!!, context: MainActivity = thisContext!!) {
            //Filter Notes first
            currentSearchState = target
            currentIsImportantState = isImportant
            var filteredList = filter(target,isImportant)
            //-------------

            //Adapt the data into lists
            thisAdapter = NotesAdapter(context, filteredList)
            listView.adapter = thisAdapter

            //If no notes to display disable clear button
            clearButton!!.isEnabled = filteredList.size!=0


            //Refresh subtitle with number of notes
            thisContext!!.supportActionBar!!.subtitle = "(${MainActivityViewModel.getDataSize()} notes)"

        }


        /**
         * Generate Random Note
         **/
        fun randomNote(){
            var data = RandomInfo()
            dataList.add(NoteData(data.getRandomTitle(),data.getRandomBody(),data.getRandomBoolean()))

            //If there's a note, enable clear button
            if (dataList.size!=0)
                clearButton!!.isEnabled = true


            //Refresh list after adding note
            displayList()
            callSnackbar(message = "Added Note #${dataList.size-1}")
            thisAdapter!!.notifyDataSetChanged()

        }

        /**
         * Clear all notes
         **/
        fun clearNotes() {
            //Get rid of all data
            dataList.clear()

            //Since no data -> no note -> disable clear button
            if (dataList.size==0)
                clearButton!!.isEnabled = false
            thisAdapter!!.notifyDataSetChanged()

            //Refresh Screen
            displayList()
            callSnackbar(message = "Cleared all notes")

        }


        /**
         * Adds a new note
         * @param title of the note
         * @param body of the note
         * @param isImportant if Note is important
         **/
        fun addNewNote(title:String,body:String,isImportant:Boolean){
            //Add new note
            dataList.add(NoteData(title,body,isImportant))

            //Refresh
            thisAdapter!!.notifyDataSetChanged()

        }

        /**
         * Replace instances of note with updated info
         * @param title of the note
         * @param body of the note
         * @param isImportant if Note is important
         **/
        fun replaceNote(title:String,body:String,isImportant:Boolean) {
            dataList.remove(NoteData.selectedNote)
            addNewNote(title,body,isImportant)
        }


        /**
         * Filter notes to provide relevant notes that meet search criteria
         * @param target keyword to search by
         * @param isImportant if Note is important
         * @return Filtered Notes
         **/
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


        /**
         * Calls snackbar view
         * @param message to be displayed
         **/
        fun callSnackbar(message: String){

            Snackbar.make(
                thisContext!!.findViewById<LinearLayout>(R.id.topBar),
                message,
                Snackbar.LENGTH_LONG
            ).setAnchorView(thisContext!!.findViewById<LinearLayout>(R.id.bottomBar))
                .show()

        }


        /**
         * @return  number of notes
         **/
        fun getDataSize(): Int {
            return dataList.size
        }




    }





}