package com.example.domain.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
data class PhotoModel(
    val id: Int,
    val pageURL: String?,
    val tags: String?,
    val previewURL: String?,
    val previewWidth: Int,
    val previewHeight: Int,
    val imageURL: String?,
    val imageWidth: Int,
    val imageHeight: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user: String?,
    val previewImageName: String,
    val fullImageName: String
)
