package com.example.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@[Module InstallIn(SingletonComponent::class)]
object CoroutineDispatchersModule {

    @[Provides Default]
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @[Provides Io]
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @[Provides Ui]
    fun provideUiDispatcher(): CoroutineDispatcher = Dispatchers.Main
}

@[Qualifier Retention(AnnotationRetention.RUNTIME)]
annotation class Io

@[Qualifier Retention(AnnotationRetention.RUNTIME)]
annotation class Ui

@[Qualifier Retention(AnnotationRetention.RUNTIME)]
annotation class Default