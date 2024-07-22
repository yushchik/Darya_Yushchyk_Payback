package com.example.domain.queryValue

interface UpdateQueryValue {
    suspend fun execute(query: String)
}
