package com.example.domain.db

import com.example.data.source.db.PersistenceDataSource
import com.example.domain.model.PhotoModel
import com.example.domain.util.toPhotoModel
import javax.inject.Inject

class GetCachedDataImpl @Inject constructor(
    private val persistenceDataSource: PersistenceDataSource
) : GetCachedData {

    override fun execute(): List<PhotoModel> =
        persistenceDataSource.loadListCachedPhoto().map { it.toPhotoModel() }
}
