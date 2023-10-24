package com.padillahirpan.helloreminder.ui.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padillahirpan.helloreminder.ui.data.db.NoteEntity

/**
 * Created by irpanpadillah on 24/10/23
 * Email: padillahirpan8@gmail.com
 */

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class HelloReminderDatabase: RoomDatabase() {


    abstract fun noteDao(): NoteDao

    companion object {
        const val db_name = "app_database"

        @Volatile
        private var Instance: HelloReminderDatabase? = null

        fun getDatabase(context: Context): HelloReminderDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HelloReminderDatabase::class.java, db_name)
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}