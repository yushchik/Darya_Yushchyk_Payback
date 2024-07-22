package com.example.domain.photo

import com.example.domain.util.SearchResult

interface GetPhoto {
    suspend fun execute(query: String): SearchResult
}
