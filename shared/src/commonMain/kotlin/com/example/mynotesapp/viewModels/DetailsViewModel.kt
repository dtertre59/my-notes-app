package com.example.mynotesapp.viewModels

import com.example.mynotesapp.database.NoteEntity
import com.example.mynotesapp.repositories.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel (noteId: String, private val repository: NoteRepository) {
    private val viewModelScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _note = MutableStateFlow<NoteEntity?>(null)
    val note: StateFlow<NoteEntity?> = _note.asStateFlow()

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _content = MutableStateFlow("")
    val content: StateFlow<String> = _content.asStateFlow()

    // 4. Usar un bloque `init` para la lógica de inicialización
    init {
        // `noteId` debe ser nullable para manejar la creación de nuevas notas
        if (noteId != null && noteId != "new") { // Comprobación para "new" si usas esa ruta
            // ¡Ahora sí puedes usar el scope que creaste!
            viewModelScope.launch {
                val fetchedNote = repository.getNoteById(noteId.toLong())
                _note.value = fetchedNote
                _title.value = fetchedNote?.title ?: ""
                _content.value = fetchedNote?.content ?: ""
            }
        }
    }

    fun onTitleChanged(newValue: String) {
        _title.value = newValue
    }

    fun onContentChanged(newValue: String) {
        _content.value = newValue
    }

    fun back(
    ) {
        if (note.value == null) {
            viewModelScope.launch {
                repository.addNote(title.value, content.value)
            }
        } else {
            viewModelScope.launch {
            repository.updateNote(note.value!!.copy(title=title.value, content=content.value))
                }
        }
    }
    fun backDelete(
    ) {
        if (note.value != null) {
            viewModelScope.launch {
                repository.deleteNote(note.value!!)
            }
        }
    }
}