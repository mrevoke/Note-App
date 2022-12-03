package com.example.noteapp.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class Mote(
    val id: UUID= UUID.randomUUID(),//98gyf6random
val title:String,
    val Description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
