package com.example.domain.db

import com.example.domain.util.PhotoDetailsResult

interface GetPhotoDetails {
    fun execute(photoId: Int): PhotoDetailsResult
}
