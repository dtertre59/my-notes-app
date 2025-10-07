package com.example.mynotesapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun getAllAsFlow(): Flow<List<NoteEntity>>

    @Query("SELECT count(*) FROM NoteEntity")
    suspend fun count(): Int

    @Insert
    suspend fun insert(item: NoteEntity)
}