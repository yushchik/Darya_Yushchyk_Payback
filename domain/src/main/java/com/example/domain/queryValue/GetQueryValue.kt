package com.example.domain.queryValue

import kotlinx.coroutines.flow.Flow

interface GetQueryValue {
    fun execute(): Flow<String>
}
