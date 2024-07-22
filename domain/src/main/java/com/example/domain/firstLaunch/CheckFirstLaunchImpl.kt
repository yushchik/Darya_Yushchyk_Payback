package com.example.domain.firstLaunch

import com.example.data.config.ConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckFirstLaunchImpl @Inject constructor(
    private val configRepository: ConfigRepository
) : CheckFirstLaunch {
    override fun execute(): Flow<Boolean> = configRepository.loadFirstLaunchStatus()
}
