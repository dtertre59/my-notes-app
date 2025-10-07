package database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotesapp.database.NotesDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<NotesDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<NotesDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}