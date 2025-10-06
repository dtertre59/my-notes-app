package com.example.mynotesapp.viewModels

import com.example.mynotesapp.presentation.Note
import com.example.mynotesapp.repositories.NoteRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel (private val repository: NoteRepository = NoteRepository()) {
    @NativeCoroutinesState
    val notes: StateFlow<List<Note>> = repository.notes
}