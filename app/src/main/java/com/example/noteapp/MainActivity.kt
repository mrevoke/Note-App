package com.example.noteapp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.Screen.NoteScreen
import com.example.noteapp.Screen.NoteViewModel
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Mote
import com.example.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                  //  modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {val noteViewModel: NoteViewModel by viewModels()
                   NotesApp(noteViewModel)
                }

                   // Greeting("Android")
                }
            }
        }
    }



@Composable
fun NotesApp(noteViewModel: NoteViewModel= viewModel()){
   val notesList=noteViewModel.getAllNotes()
    NoteScreen(notes = notesList
        , onRemoveNote = {
           noteViewModel.removeNote(it)
        },
        onAddNote = {
           noteViewModel.addNote(it)
        })

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {
        //Greeting("Android")
    }
}