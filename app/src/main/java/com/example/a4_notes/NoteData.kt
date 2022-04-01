package com.example.a4_notes
 /**
  * Individual Note unit responsible for note information and tracking which note is selected
  **/
 data class NoteData(
    var title:String,
    var body:String,
    var isImportant:Boolean = false


){
     companion object{
         var selectedNote : NoteData? = null
     }
 }

