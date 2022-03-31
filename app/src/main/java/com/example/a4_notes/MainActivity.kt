package com.example.a4_notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
//    private lateinit var listView : ListView
    private lateinit var viewModel: MainActivityViewModel

    companion object {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "A4 Notes (20987455)"
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        MainActivityViewModel.thisContext = this
        setContentView(R.layout.activity_main)

    }

//    fun deleteNote(button: View){
//        print("I was called")
//        viewModel.deleteNote(button)
//    }






}