package com.example.data.di

import androidx.work.CoroutineWorker
import com.example.data.config.ConfigRepository
import com.example.data.config.ConfigRepositoryImpl
import com.example.data.download.DownloadPhotoWorker
import com.example.data.file.FilesDataSource
import com.example.data.file.FilesDataSourceImpl
import com.example.data.source.ApiDataSource
import com.example.data.source.ApiDataSourceImpl
import com.example.data.source.db.PersistenceDataSource
import com.example.data.source.db.PersistenceDataSourceImpl
import com.example.data.source.preferences.PreferencesDataSource
import com.example.data.source.preferences.PreferencesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataModule {
    @Binds
    abstract fun bindApiDataSource(
        apiDataSource: ApiDataSourceImpl
    ): ApiDataSource
    @Binds
    abstract fun bindPersistenceDataSource(
        persistenceDataSource: PersistenceDataSourceImpl
    ): PersistenceDataSource

    @Binds
    abstract fun bindDownloadPhotoWorker(
        downloadPhotoWorker: DownloadPhotoWorker
    ): CoroutineWorker

    @Binds
    abstract fun bindFilesDataSource(
        filesDataSourceImpl: FilesDataSourceImpl
    ): FilesDataSource

    @Binds
    abstract fun bindPreferencesDataSource(
        preferencesDataSourceImpl: PreferencesDataSourceImpl
    ): PreferencesDataSource

    @Binds
    abstract fun bindConfigRepository(
        configRepositoryImpl: ConfigRepositoryImpl
    ): ConfigRepository
}
