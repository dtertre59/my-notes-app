package com.example.mynotesapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun getAllAsFlow(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}