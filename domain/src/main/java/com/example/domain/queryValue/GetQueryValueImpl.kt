package com.example.domain.queryValue

import com.example.data.config.ConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQueryValueImpl @Inject constructor(
    private val configRepository: ConfigRepository
) : GetQueryValue {
    override fun execute(): Flow<String> = configRepository.loadQueryValue()
}
