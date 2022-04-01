package com.example.a4_notes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar



/**
 * The home screen fragment.
 */
class ListFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Updates view model to notify that it's the active fragment
        MainActivityViewModel.HomeFragmentView = view

        /**
         * Clicking addButton causes navigation to new fragment
         */
        view.findViewById<Button>(R.id.addButton).setOnClickListener{

            Navigation.findNavController(view).navigate(
                R.id.navigateToUpdateFragment,
            )
            //Notifies the addButton is called to toggle textHeader
            MainActivityViewModel.addButtonCalled = true
        }


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Accessing Views
        var randomButton = view.findViewById<Button>(R.id.randomButton)
        var clearButton = view.findViewById<Button>(R.id.clearButton)
        var listView = view.findViewById<ListView>(R.id.noteView)

        //Updating state to view model
        MainActivityViewModel.classListView = listView
        MainActivityViewModel.clearButton = clearButton

        //Acessing views used for search
        var isImportantSwitch = view.findViewById<Switch>(R.id.switchToggle)
        var searchField = view.findViewById<EditText>(R.id.searchField)

        /**
         * Clicking the searchField or toggleSwitch initiates the filtering process in ViewModel
         */
        searchField.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                MainActivityViewModel.displayList(searchField.text.toString(),isImportantSwitch.isChecked)
            }
        })

        isImportantSwitch.setOnClickListener{
            MainActivityViewModel.displayList(searchField.text.toString(),isImportantSwitch.isChecked)
        }

        //Refresh display
        MainActivityViewModel.displayList(searchField.text.toString(),isImportantSwitch.isChecked)


        //Request for a new random note
        randomButton.setOnClickListener{
            MainActivityViewModel.randomNote()
        }
        //Request for note clearing process
        clearButton.setOnClickListener{
            MainActivityViewModel.clearNotes()
        }









    }










    companion object {


        fun navigate(noteData: NoteData, position: Int) {

            Navigation.findNavController(
                MainActivityViewModel.HomeFragmentView!!).navigate(R.id.action_listFragment_to_readNoteFragment,
                args = Bundle().apply {
                    putString("title",noteData.title)
                    putString("body",noteData.body)
                    putBoolean("isImportant",noteData.isImportant)
                    putInt("position",position)
                }
            )
        }


    }
}