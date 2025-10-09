package com.example.mynotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mynotesapp.database.NoteEntity
import com.example.mynotesapp.repositories.NoteRepository
import database.getDatabaseBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val builder = getDatabaseBuilder(applicationContext)
        val db = builder.build()
//        val db = getNotesDatabase(applicationContext)

        val dao = db.getDao()
        val noteRepository = NoteRepository(dao)

        setContent {
            MaterialTheme {
                NotesApp(noteRepository)
            }
        }
    }
}


// COMO HACER LA PREVIEW DE ESTO? HAY QUE SIMULAR LA DDBB
//@Preview
//@Composable
//fun AppAndroidPreview() {
//    NotesApp()
//}