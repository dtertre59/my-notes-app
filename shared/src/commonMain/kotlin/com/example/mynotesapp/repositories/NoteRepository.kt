package com.example.mynotesapp.repositories

import com.example.mynotesapp.database.NoteDao
import com.example.mynotesapp.database.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository (private val _noteDao: NoteDao) {

    val notes: Flow<List<NoteEntity>> = _noteDao.getAllAsFlow()

    suspend fun getNoteById(id: Long): NoteEntity? {
        return _noteDao.getNoteById(id)
    }

    suspend fun addNote(title: String, content: String) {
        val note = NoteEntity(title = title, content = content)
        _noteDao.addNote(note)
    }

    suspend fun updateNote(note: NoteEntity) {
        _noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        _noteDao.deleteNote(note)
    }
}