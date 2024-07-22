package com.example.data.source.db

import com.example.data.entities.db.CachedPhoto
import kotlinx.coroutines.flow.Flow

interface PersistenceDataSource {
    suspend fun insertCachedPhoto(cachedPhotoList: List<CachedPhoto>)
    fun loadListCachedPhoto(): List<CachedPhoto>
    fun loadCachedPhoto(photoId: Int): CachedPhoto?
}
