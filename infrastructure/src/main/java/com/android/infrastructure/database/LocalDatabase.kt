package com.android.infrastructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.infrastructure.dao.PointDao
import com.android.infrastructure.entities.Point

@Database(entities = arrayOf(Point::class), version = 1, exportSchema = false)
internal abstract class LocalDatabase: RoomDatabase() {

    abstract fun pointDao(): PointDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null
        fun getDatabase(context: Context): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "local_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}