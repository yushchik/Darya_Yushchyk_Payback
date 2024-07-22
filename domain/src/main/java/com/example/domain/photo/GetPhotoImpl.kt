package com.example.domain.photo

import com.example.data.Constants.PHOTO_PREVIEW_FOLDER_NAME
import com.example.data.file.FilesDataSource
import com.example.data.source.ApiDataSource
import com.example.data.source.db.PersistenceDataSource
import com.example.domain.util.SearchResult
import com.example.domain.util.toDbPhotoModel
import com.example.domain.util.toPhotoModel
import java.io.IOException
import javax.inject.Inject

class GetPhotoImpl @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val persistenceDataSource: PersistenceDataSource,
    private val loadPhoto: LoadPhoto,
    private val filesDataSource: FilesDataSource
) : GetPhoto {

    override suspend fun execute(query: String): SearchResult = try {
        val results = apiDataSource.getPhoto(query).hits
        persistenceDataSource.insertCachedPhoto(results.map { it.toDbPhotoModel() })
        filesDataSource.deleteFiles()
        loadPhoto.execute(results.map { it.toPhotoModel() })
        SearchResult.Success(results = results.map { it.toPhotoModel() })
    } catch (e: IOException) {
        SearchResult.Error
    }
}
