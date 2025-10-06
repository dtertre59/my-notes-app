package com.example.mynotesapp.viewModels

import com.example.mynotesapp.presentation.Note
import com.example.mynotesapp.repositories.NoteRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel (noteId: String, private val repository: NoteRepository = NoteRepository()) {
    @NativeCoroutinesState
    val notes: StateFlow<List<Note>> = repository.notes

    val note = repository.getNoteById(noteId)

    private val _title = MutableStateFlow(note?.title ?: "")
    val title: StateFlow<String> = _title

    private val _content= MutableStateFlow(note?.content ?: "")
    val content: StateFlow<String> = _content

    fun onTitleChanged(newValue: String) {
        _title.value = newValue
    }

    fun onContentChanged(newValue: String) {
        _content.value = newValue
    }

    fun back(
    ) {
        if (note == null) {
            repository.addNote(title.value, content.value, 0xFFE57373)
        } else {
            repository.updateNote(note.copy(title=title.value, content=content.value))
        }
    }
    fun backDelete(
    ) {
        if (note != null) {
            repository.deleteNote(note)
        }
    }
}