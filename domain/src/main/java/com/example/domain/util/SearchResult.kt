package com.example.domain.util

import com.example.domain.model.PhotoModel

sealed interface SearchResult {
    data object Error : SearchResult
    data class Success(val results: List<PhotoModel>) : SearchResult
}

sealed interface PhotoDetailsResult {
    data object Error : PhotoDetailsResult
    data class Success(val photoDetails: PhotoModel) : PhotoDetailsResult
}
