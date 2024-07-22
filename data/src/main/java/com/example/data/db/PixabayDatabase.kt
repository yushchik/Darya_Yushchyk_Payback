package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.entities.db.CachedPhoto

@Database(
    entities = [CachedPhoto::class],
    version = 1,
    exportSchema = true
)
abstract class PixabayDatabase : RoomDatabase() {
    abstract fun cachedPhotoDao(): CachedPhotoDao
}
