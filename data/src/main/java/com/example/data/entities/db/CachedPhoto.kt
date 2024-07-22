package com.example.data.entities.db

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName = "cached_photo")
data class CachedPhoto(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "photoId") val photoId: Int,
    @ColumnInfo(name = "pageURL") val pageURL: String?,
    @ColumnInfo(name = "tags") val tags: String?,
    @ColumnInfo(name = "previewURL") val previewURL: String?,
    @ColumnInfo(name = "previewWidth") val previewWidth: Int,
    @ColumnInfo(name = "previewHeight") val previewHeight: Int,
    @ColumnInfo(name = "imageURL") val imageURL: String?,
    @ColumnInfo(name = "imageWidth") val imageWidth: Int,
    @ColumnInfo(name = "imageHeight") val imageHeight: Int,
    @ColumnInfo(name = "downloads") val downloads: Int,
    @ColumnInfo(name = "likes") val likes: Int,
    @ColumnInfo(name = "comments") val comments: Int,
    @ColumnInfo(name = "user") val user: String?
)