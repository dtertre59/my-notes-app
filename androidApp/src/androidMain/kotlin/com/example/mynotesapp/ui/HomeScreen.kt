package com.example.mynotesapp.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynotesapp.presentation.Note
import com.example.mynotesapp.ui.components.NoteCard
import com.example.mynotesapp.viewModels.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onEditNote: (Long) -> Unit, viewModel: HomeViewModel) {
    val notes by viewModel.notes.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Text("Number of Notes: ${notes.size}")
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
//                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            items(notes) { note ->
                NoteCard(note, modifier = Modifier
                    .clickable {
//                        Log.e("1", "Note ${note.id} Button Clicked")
                        onEditNote(note.id)
                    }
                )
            }
        }
    }
}

// TODO
//@Preview(showBackground = false)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(onEditNote = {}, viewModel = HomeViewModel())
//}