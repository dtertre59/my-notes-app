package database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.mynotesapp.database.NotesDatabase
import kotlinx.coroutines.Dispatchers


fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<NotesDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("my_notes_room.db")
    return Room.databaseBuilder<NotesDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}


// SOLO PARA ANDROID. ASI NO HAY QUE HACER EL BUILD EN EL MAINACTIVITY
//fun getNotesDatabase(context: Context): NotesDatabase {
//    val appContext = context.applicationContext
//    val dbFile = appContext.getDatabasePath("my_notes_room.db")
//    return Room.databaseBuilder<NotesDatabase>(
//        context = appContext,
//        name = dbFile.absolutePath
//    )
//        .setDriver(BundledSQLiteDriver())
//        .build()
//}
