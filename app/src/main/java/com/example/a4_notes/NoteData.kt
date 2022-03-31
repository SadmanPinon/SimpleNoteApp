package com.example.a4_notes

 data class NoteData(
    var title:String,
    var body:String,
    var isImportant:Boolean = false


){
     companion object{
         var selectedNote : NoteData? = null
     }
 }

