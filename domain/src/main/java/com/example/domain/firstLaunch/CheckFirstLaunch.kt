package com.example.domain.firstLaunch

import kotlinx.coroutines.flow.Flow

interface CheckFirstLaunch {
    fun execute(): Flow<Boolean>
}
