package com.example.noteapp.data

import com.example.noteapp.model.Mote

class NoteDataSource {
    fun loadNotes(): List<Mote> {
    return listOf(
        Mote(title = "CLICK TO DELETE", Description = "To delete any note just tap on it ")

        )
    }

}