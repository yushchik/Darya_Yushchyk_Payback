package com.example.domain.firstLaunch

import com.example.data.config.ConfigRepository
import javax.inject.Inject

class UpdateFirstLaunchImpl @Inject constructor(
    private val configRepository: ConfigRepository
) : UpdateFirstLaunch {
    override suspend fun execute() {
        configRepository.updateFirstLaunchStatus()
    }
}
