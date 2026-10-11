package com.abdullojon.eventapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdullojon.eventapp.data.local.dao.EventDao
import com.abdullojon.eventapp.data.local.entity.EventStateEntity

@Database(
    entities = [EventStateEntity::class],
    version = 3,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
