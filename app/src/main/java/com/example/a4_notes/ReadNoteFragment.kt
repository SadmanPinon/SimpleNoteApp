package com.example.a4_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReadNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReadNoteFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_read_note, container, false)


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var title = view.findViewById<TextView>(R.id.t_noteTitleText)
        var body = view.findViewById<TextView>(R.id.t_noteBodyText)
        var isImporantChip = view.findViewById<Chip>(R.id.t_isImportantChip)
        var header = view.findViewById<TextView>(R.id.t_noteNumberText)
        var editButton = view.findViewById<Button>(R.id.t_noteEditButton)

        arguments!!.apply {
            title.text = getString("title")
            body.text = getString("body")
            if (!getBoolean("isImportant"))
            { isImporantChip.visibility = View.INVISIBLE}
            else {isImporantChip.visibility = View.VISIBLE}

            header.text = "Note #${getInt("position")}"
        }

        editButton.setOnClickListener{
            Navigation.findNavController(view).navigate(
                R.id.action_readNoteFragment_to_updateFragment,
                args = Bundle().apply {
                    putString("title",arguments!!.getString("title"))
                    putString("body",arguments!!.getString("body"))
                    putBoolean("isImportant",arguments!!.getBoolean("isImportant"))
                    putInt("position",arguments!!.getInt("position"))
                    putString("buttonName","Update")
                }

            )
        }



    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReadNoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReadNoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}