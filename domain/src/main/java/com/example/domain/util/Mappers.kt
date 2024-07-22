package com.example.domain.util

import com.example.data.entities.api.HitsData
import com.example.data.entities.db.CachedPhoto
import com.example.domain.model.PhotoModel

fun HitsData.toDbPhotoModel() = CachedPhoto(
    photoId = id,
    pageURL = pageURL,
    tags = tags,
    previewURL = previewURL,
    previewWidth = previewWidth,
    previewHeight = previewHeight,
    imageURL = webformatURL,
    imageWidth = webformatWidth,
    imageHeight = webformatHeight,
    downloads = downloads,
    likes = likes,
    comments = comments,
    user = user
)

fun HitsData.toPhotoModel() = PhotoModel(
    id = id,
    pageURL = pageURL,
    tags = tags,
    previewURL = previewURL,
    previewWidth = previewWidth,
    previewHeight = previewHeight,
    imageURL = webformatURL,
    imageWidth = webformatWidth,
    imageHeight = webformatHeight,
    downloads = downloads,
    likes = likes,
    comments = comments,
    user = user,
    previewImageName = "${id}.${previewURL!!.split(".").last()}",
    fullImageName = "${id}.${webformatURL!!.split(".").last()}"
)

fun CachedPhoto.toPhotoModel() = PhotoModel(
    id = photoId,
    pageURL = pageURL,
    tags = tags,
    previewURL = previewURL,
    previewWidth = previewWidth,
    previewHeight = previewHeight,
    imageURL = imageURL,
    imageWidth = imageWidth,
    imageHeight = imageHeight,
    downloads = downloads,
    likes = likes,
    comments = comments,
    user = user,
    previewImageName = "${photoId}.${previewURL!!.split(".").last()}",
    fullImageName = "${photoId}.${imageURL!!.split(".").last()}"
)
