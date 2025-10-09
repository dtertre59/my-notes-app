package com.example.mynotesapp.viewModels

import com.example.mynotesapp.database.NoteEntity
import com.example.mynotesapp.presentation.Note
import com.example.mynotesapp.repositories.NoteRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: NoteRepository) {
    val notes: StateFlow<List<NoteEntity>> = repository.notes
        .stateIn(
            scope = CoroutineScope(Dispatchers.Main), // Scope para la coroutine
            started = SharingStarted.Lazily,         // Cuándo empezar a recolectar
            initialValue = emptyList()               // Valor inicial antes de que llegue algo
        )
}