package com.example.domain.queryValue

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QueryValueInteractor @Inject constructor(
    private val getQueryValue: GetQueryValue,
    private val updateQueryValue: UpdateQueryValue
) {
    fun getQueryValue(): Flow<String> = getQueryValue.execute()
    suspend fun updateQueryValue(query: String) = updateQueryValue.execute(query)
}
