package com.example.domain.db

import com.example.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface GetCachedData {
    fun execute(): List<PhotoModel>
}
