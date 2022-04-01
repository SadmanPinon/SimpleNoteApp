package com.example.a4_notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    companion object {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "A4 Notes (20987455)" //Setting title
        //Initialize ViewModel
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        MainActivityViewModel.thisContext = this //Set Context for ViewModel
        setContentView(R.layout.activity_main)



    }

}