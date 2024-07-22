package com.example.domain.firstLaunch

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirstLaunchInteractor @Inject constructor(
    private val checkFirstLaunch: CheckFirstLaunch,
    private val updateFirstLaunch: UpdateFirstLaunch
) {
    fun firstLaunch(): Flow<Boolean> = checkFirstLaunch.execute()
    suspend fun onFirstLaunch() = updateFirstLaunch.execute()
}
