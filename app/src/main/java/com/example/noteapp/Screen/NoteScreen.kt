package com.example.noteapp.Screen

import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.components.NoteInputApp
import com.example.noteapp.components.Notebutton
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Mote
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(notes: List<Mote>,
onAddNote: (Mote)->Unit,
               onRemoveNote:(Mote)->Unit
){

    var title: String by remember {
        mutableStateOf("")
    }
    var descrip by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current

Column(modifier = Modifier.padding(6.dp)) {
TopAppBar(title={
                Text(text = stringResource(id = R.string.app_name))
}, actions = {
    Icon(imageVector = Icons.Rounded.Notifications,
        contentDescription = "icons")
},
backgroundColor = Color(0xFF03A9F4)
    )
//Content
    Column(modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally){

        NoteInputApp(
            modifier = Modifier.padding(top = 9.dp,
        bottom = 8.dp),text = title,
            label = "Title",
            onTextChange ={
                if (it.all { char->
                        char.isLetter()||char.isWhitespace()
                    })title=it
            } )



    NoteInputApp(
        modifier = Modifier.padding(top = 9.dp,
        bottom = 8.dp),text = descrip,
        label = "Add a note",
        onTextChange ={if (it.all { char->
                char.isLetter()||char.isWhitespace()
            })descrip=it} )
    
    Notebutton(text = "Save", onClick = {if (title.isNotEmpty() && descrip.isNotEmpty()){
//save to list
        onAddNote(Mote(title= title, Description = descrip))
      title=""
        descrip=""
  Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
    } })

}
    Divider(modifier = Modifier.padding(10.dp))
    LazyColumn{
        items(notes){note->
           // Text(text = note.title)
            NoteRow(note = note, onNoteClicked = {
                onRemoveNote(it)
            })
        }
    }
}
}

@Composable
fun NoteRow
            (

    modifier: Modifier=Modifier,
    note:Mote,
    onNoteClicked: (Mote)->Unit
){
        Surface(
            modifier
                .padding(5.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = 33.dp,
                        bottomStart = 33.dp
                    )
                )
                .fillMaxWidth(),
            color = Color(0xFF0AD9F3),
            elevation = 6.dp
        ) {
            Column(
                modifier
                    .clickable {onNoteClicked(note) }
                    .padding(
                        horizontal = 14.dp,
                        vertical = 6.dp
                    ),
            horizontalAlignment = Alignment.Start) {
                Text(text = note.title,
                    style = MaterialTheme.typography.subtitle2)
                Text(text = note.Description,
                    style = MaterialTheme.typography.subtitle1)
                Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),

                    style = MaterialTheme.typography.caption)


            }

        }
            }


@Preview(showBackground = true)
@Composable
fun NoteScreenPre(){

    NoteScreen(notes = NoteDataSource().loadNotes()
        , onAddNote = {},
    onRemoveNote = {})
}