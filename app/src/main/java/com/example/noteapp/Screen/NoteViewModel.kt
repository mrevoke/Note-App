package com.example.noteapp.Screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Mote

class NoteViewModel: ViewModel() {
private var noteList= mutableStateListOf<Mote>()

    init {
      noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note:Mote){
        noteList.add(note)
    }
fun removeNote(note: Mote){
    noteList.remove(note)
}
    fun getAllNotes():List<Mote>{
        return noteList
    }

}