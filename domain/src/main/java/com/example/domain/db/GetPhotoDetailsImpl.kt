package com.example.domain.db

import com.example.data.source.db.PersistenceDataSource
import com.example.domain.util.PhotoDetailsResult
import com.example.domain.util.toPhotoModel
import java.io.IOException
import javax.inject.Inject

class GetPhotoDetailsImpl @Inject constructor(
    private val persistenceDataSource: PersistenceDataSource
) : GetPhotoDetails {

    override fun execute(photoId: Int) = try {
        val results = persistenceDataSource.loadCachedPhoto(photoId)
        if (results != null)
            PhotoDetailsResult.Success(photoDetails = results.toPhotoModel())
        else
            PhotoDetailsResult.Error
    } catch (e: IOException) {
        PhotoDetailsResult.Error
    }
}
