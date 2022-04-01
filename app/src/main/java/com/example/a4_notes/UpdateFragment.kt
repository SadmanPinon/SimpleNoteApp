package com.example.a4_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.navigation.Navigation


/**
 * Fragment responsible for adding or Editing a note
 */

class UpdateFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        //Acessing views to modify data
        var headerText = view.findViewById<TextView>(R.id.headerText)
        var editText = view.findViewById<EditText>(R.id.titleText)
        var bodytext = view.findViewById<EditText>(R.id.bodyText)
        var isImportantView =  view.findViewById<Switch>(R.id.isImportantView)

        //If data given to pre-populate fetch and update views
        headerText.text = "Add new Note"
        if (arguments!=null){
            arguments?.apply {
                editText.setText(getString("title"))
                bodytext.setText(getString("body"))
                isImportantView.isChecked = getBoolean("isImportant")
                headerText.text = "Edit Note #${getInt("position")}"
            }

        }

        //Change HeaderText depending on previous fragment in stack
        if (MainActivityViewModel.addButtonCalled){
            headerText.text = "Add new note"
            MainActivityViewModel.addButtonCalled = false
        }

        //Update note or create new one
        view.findViewById<Button>(R.id.updateButton).setOnClickListener{
            if (arguments!=null) {
                MainActivityViewModel.replaceNote(
                    editText.text.toString(),
                    bodytext.text.toString(),
                    isImportantView.isChecked
                )
            }
            else{
                MainActivityViewModel.addNewNote(
                    editText.text.toString(),
                    bodytext.text.toString(),
                    isImportantView.isChecked
                )

            }

            Navigation.findNavController(view).navigate(R.id.navigateToHomeFragment)

        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}