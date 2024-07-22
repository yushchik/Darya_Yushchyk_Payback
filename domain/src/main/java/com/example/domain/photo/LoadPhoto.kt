package com.example.domain.photo

import com.example.domain.model.PhotoModel

interface LoadPhoto {
    suspend fun execute(result: List<PhotoModel>)
}
