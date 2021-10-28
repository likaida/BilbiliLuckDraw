package com.aceli.bilibililuckdraw.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aceli.bilibililuckdraw.LuckDrawApplication
import com.aceli.bilibililuckdraw.database.dao.VideoInfoDao
import com.aceli.bilibililuckdraw.database.dao.WordDao
import com.aceli.bilibililuckdraw.database.entity.VideoInfoEntity
import com.aceli.bilibililuckdraw.database.entity.WordEntity
import com.aceli.bilibililuckdraw.widget.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [WordEntity::class, VideoInfoEntity::class], version = 1, exportSchema = false)
abstract class AceRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun videoInfo(): VideoInfoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AceRoomDatabase? = null

        fun initDataBase(context: Context, scope: CoroutineScope): AceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AceRoomDatabase::class.java,
                    "ace_database"
                ).addCallback(AceDatabaseCallBack(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun getDataBase(): AceRoomDatabase? {
            return if (INSTANCE == null) {
                initDataBase(LuckDrawApplication.mApp,LuckDrawApplication.applicationScope)
            } else {
                INSTANCE
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

