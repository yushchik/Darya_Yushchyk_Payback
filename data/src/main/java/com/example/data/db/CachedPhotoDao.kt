package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.entities.db.CachedPhoto

@Dao
interface CachedPhotoDao {
    @Query("SELECT * FROM cached_photo")
    fun loadListCachedPhoto(): List<CachedPhoto>

    @Query("SELECT * FROM cached_photo WHERE photoId = :photoId")
    fun loadCachedPhoto(photoId: Int): CachedPhoto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCachedPhoto(savedArticle: CachedPhoto)

    @Transaction
    suspend fun insertCachedPhotoList(items: List<CachedPhoto>) {
        deleteAllArticles()
        items.forEach { insertCachedPhoto(it) }
    }

    @Query("DELETE FROM cached_photo")
    suspend fun deleteAllArticles()
}
