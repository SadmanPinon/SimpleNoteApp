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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        var headerText = view.findViewById<TextView>(R.id.headerText)

        var editText = view.findViewById<EditText>(R.id.titleText)
        var bodytext = view.findViewById<EditText>(R.id.bodyText)
        var isImportantView =  view.findViewById<Switch>(R.id.isImportantView)


        if (arguments!=null){
            arguments?.apply {
                editText.setText(getString("title"))
                bodytext.setText(getString("body"))
                isImportantView.isChecked = getBoolean("isImportant")
                headerText.setText("Edit Note #${getString("position")}")
            }

        }

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




        if (MainActivityViewModel.addButtonCalled){
            headerText.text = "Add new Note"
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UpdateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpdateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}