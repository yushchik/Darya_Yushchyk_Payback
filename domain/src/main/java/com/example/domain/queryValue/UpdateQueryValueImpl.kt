package com.example.domain.queryValue

import com.example.data.config.ConfigRepository
import javax.inject.Inject

class UpdateQueryValueImpl @Inject constructor(
    private val configRepository: ConfigRepository
) : UpdateQueryValue {
    override suspend fun execute(query: String) {
        configRepository.updateQueryValue(query)
    }
}
