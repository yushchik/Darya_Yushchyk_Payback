package com.example.data.source.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataSourceImpl @Inject constructor(
    private val preferencesDataStore: DataStore<Preferences>
) : PreferencesDataSource {
    override fun <T : Any> loadPreference(key: Preferences.Key<T>): Flow<T?> {
        return preferencesDataStore.data
            .map { it[key] }
    }

    override suspend fun <T : Any> savePreference(key: Preferences.Key<T>, value: T) {
        preferencesDataStore.edit {
            it[key] = value
        }
    }

    override suspend fun <T : Any> clearPreference(key: Preferences.Key<T>) {
        preferencesDataStore.edit {
            it.remove(key)
        }
    }
}
