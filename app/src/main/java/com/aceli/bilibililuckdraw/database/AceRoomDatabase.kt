package com.aceli.bilibililuckdraw.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aceli.bilibililuckdraw.database.dao.WordDao
import com.aceli.bilibililuckdraw.database.entity.WordEntity

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
public abstract class AceRoomDatabase: RoomDatabase()  {
    abstract fun wordDao(): WordDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AceRoomDatabase? = null

        fun getDatabase(context: Context): AceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AceRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}