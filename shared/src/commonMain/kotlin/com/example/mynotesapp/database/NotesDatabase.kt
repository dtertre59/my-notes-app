package com.example.mynotesapp.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [NoteEntity::class], version = 1)
@ConstructedBy(NotesDatabaseConstructor::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun getDao(): NoteDao
}

// The Room compiler generates the `actual` implementations. using function getRoomDatabase?
@Suppress("KotlinNoActualForExpect")
expect object NotesDatabaseConstructor : RoomDatabaseConstructor<NotesDatabase> {
    override fun initialize(): NotesDatabase
}


fun getRoomDatabase(
    builder: RoomDatabase.Builder<NotesDatabase>
): NotesDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}