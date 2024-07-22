package com.example.data.config

import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    fun loadQueryValue(): Flow<String>
    suspend fun updateQueryValue(query: String)
    fun loadFirstLaunchStatus(): Flow<Boolean>
    suspend fun updateFirstLaunchStatus()
}
