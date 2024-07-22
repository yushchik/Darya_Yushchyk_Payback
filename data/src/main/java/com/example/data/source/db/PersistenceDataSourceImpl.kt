package com.example.data.source.db

import com.example.data.db.PixabayDatabase
import com.example.data.entities.db.CachedPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersistenceDataSourceImpl @Inject constructor(
    private val pixabayDatabase: PixabayDatabase
) : PersistenceDataSource {
    override suspend fun insertCachedPhoto(cachedPhotoList: List<CachedPhoto>) =
        pixabayDatabase.cachedPhotoDao().insertCachedPhotoList(cachedPhotoList)

    override fun loadListCachedPhoto() =
        pixabayDatabase.cachedPhotoDao().loadListCachedPhoto()

    override fun loadCachedPhoto(photoId: Int) =
        pixabayDatabase.cachedPhotoDao().loadCachedPhoto(photoId)
}
