package com.example.a4_notes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        MainActivityViewModel.HomeFragmentView = view
        view.findViewById<Button>(R.id.addButton).setOnClickListener{
            MainActivityViewModel.addButtonCalled = true
            Navigation.findNavController(view).navigate(R.id.navigateToUpdateFragment)
        }



        return view




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var randomButton = view.findViewById<Button>(R.id.randomButton)
        var viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        var clearButton = view.findViewById<Button>(R.id.clearButton)
//        val addButton = view.findViewById<Button>(R.id.addButton)
        var listView = view.findViewById<ListView>(R.id.noteView)
        MainActivityViewModel.classListView = listView
        MainActivityViewModel.clearButton = clearButton


        var isImportantSwitch = view.findViewById<Switch>(R.id.switchToggle)
        var searchField = view.findViewById<EditText>(R.id.searchField)
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


        MainActivityViewModel.displayList(searchField.text.toString(),isImportantSwitch.isChecked)

        randomButton.setOnClickListener{
            MainActivityViewModel.randomNote()
        }
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic

        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}