package com.example.data.config

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.data.di.Io
import com.example.data.source.preferences.PreferencesDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    @Io private val ioDispatcher: CoroutineDispatcher
) : ConfigRepository {
    override fun loadQueryValue() = preferencesDataSource.loadPreference(
        QUERY_VALUE_KEY
    )
        .flowOn(ioDispatcher)
        .map { it ?: "" }

    override suspend fun updateQueryValue(query: String) = withContext(ioDispatcher) {
        preferencesDataSource.savePreference(QUERY_VALUE_KEY, query)
    }

    override fun loadFirstLaunchStatus() = preferencesDataSource.loadPreference(
        FIRST_LAUNCH_KEY
    )
        .flowOn(ioDispatcher)
        .map { it ?: true }

    override suspend fun updateFirstLaunchStatus() = withContext(ioDispatcher) {
        preferencesDataSource.savePreference(FIRST_LAUNCH_KEY, false)
    }

    companion object {
        private val FIRST_LAUNCH_KEY = booleanPreferencesKey("firstLaunch")
        private val QUERY_VALUE_KEY = stringPreferencesKey("queryValue")
    }
}
