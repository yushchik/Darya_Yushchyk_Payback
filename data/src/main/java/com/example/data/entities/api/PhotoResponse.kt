package com.example.data.entities.api

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "total")
    val total: Int,
    @Json(name = "totalHits")
    val totalHits: Int,
    @Json(name = "hits")
    val hits: List<HitsData>
)

@Keep
@JsonClass(generateAdapter = true)
data class HitsData(
    @Json(name = "id")
    val id: Int,
    @Json(name = "pageURL")
    val pageURL: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "tags")
    val tags: String?,
    @Json(name = "previewURL")
    val previewURL: String?,
    @Json(name = "previewWidth")
    val previewWidth: Int,
    @Json(name = "previewHeight")
    val previewHeight: Int,
    @Json(name = "webformatURL")
    val webformatURL: String?,
    @Json(name = "webformatWidth")
    val webformatWidth: Int,
    @Json(name = "webformatHeight")
    val webformatHeight: Int,
    @Json(name = "largeImageURL")
    val largeImageURL: String?,
    @Json(name = "fullHDURL")
    val fullHDURL: String?,
    @Json(name = "imageURL")
    val imageURL: String?,
    @Json(name = "imageWidth")
    val imageWidth: Int,
    @Json(name = "imageSize")
    val imageSize: Int,
    @Json(name = "imageHeight")
    val imageHeight: Int,
    @Json(name = "views")
    val views: Int,
    @Json(name = "downloads")
    val downloads: Int,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "comments")
    val comments: Int,
    @Json(name = "user_id")
    val user_id: Int,
    @Json(name = "user")
    val user: String?,
    @Json(name = "userImageURL")
    val userImageURL: String?
)
