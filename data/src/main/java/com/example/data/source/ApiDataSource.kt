package com.example.data.source

import com.example.data.entities.api.PhotoResponse

interface ApiDataSource {
    suspend fun getPhoto(query: String): PhotoResponse
}
