package com.example.data.source.preferences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    fun <T : Any> loadPreference(key: Preferences.Key<T>): Flow<T?>
    suspend fun <T : Any> savePreference(key: Preferences.Key<T>, value: T)
    suspend fun <T : Any> clearPreference(key: Preferences.Key<T>)
}
