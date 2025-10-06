package com.example.mynotesapp.repositories

import com.example.mynotesapp.presentation.Note
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteRepository {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    @NativeCoroutinesState
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private var _counter: Int = 0

    init {
        _notes.value = listOf(
            Note(
                id = "0",
                title = "Nota de ejemplo",
                content = "Esta nota ya existe al iniciar la app",
                colorHex = 0xFFE57373
            ),
            Note(
                id = "1",
                title = "Segunda nota",
                content = "Esta es otra nota precargada",
                colorHex = 0xFF81C784
            )
        )
        _counter = _notes.value.size
    }

    fun addNote(title: String, content: String, colorHex: Long) {
        val note = Note(
            id = _counter.toString(),
            title = title,
            content = content,
            colorHex = colorHex
        )
        _notes.value += note
        _counter++
    }

    @NativeCoroutines
    fun getNotes(): MutableStateFlow<List<Note>> = _notes

    fun getNoteById(id: String): Note? {
        return _notes.value.find { it.id == id }
    }

    fun updateNote(note: Note) {
        val id = note.id
        _notes.update { list ->
            list.map {
                if (it.id == id) {
                    it.copy(
                        title = note.title,
                        content = note.content,
                        colorHex = note.colorHex
                    )
                } else {
                    it
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        _notes.value = _notes.value.filter { it.id != note.id }
    }
}