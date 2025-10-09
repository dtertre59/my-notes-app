package com.example.mynotesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynotesapp.database.NoteEntity

import com.example.mynotesapp.presentation.Note

@Composable
fun NoteCard(note: NoteEntity, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F0F0), RoundedCornerShape(16.dp))
            .padding(16.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = note.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview(showBackground = false)
@Composable
fun NoteCardPreview() {
    val note = NoteEntity(
        title = "My Note",
        content = "This is the content of my note.",
    )

    NoteCard(note = note)
}