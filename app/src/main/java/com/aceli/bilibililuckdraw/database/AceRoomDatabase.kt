package com.aceli.bilibililuckdraw.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aceli.bilibililuckdraw.database.dao.WordDao
import com.aceli.bilibililuckdraw.database.entity.WordEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
public abstract class AceRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AceRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AceRoomDatabase::class.java,
                    "word_database"
                ).addCallback(AceDatabaseCallBack(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AceDatabaseCallBack(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete all content here.
            wordDao.deleteAll()

            // Add sample words.
            var word = WordEntity("Hello")
            wordDao.insert(word)
            word = WordEntity("World!")
            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }
}

