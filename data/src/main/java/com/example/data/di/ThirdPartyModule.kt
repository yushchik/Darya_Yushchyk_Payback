package com.example.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.data.api.PixabayApi
import com.example.data.db.PixabayDatabase
import com.example.data.entities.RegexAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import logcat.logcat
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object ThirdPartyModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(RegexAdapter())
        .build()

    @Provides
    @Singleton
    fun providePreferencesDatastore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("pixabay-prefs")
        }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor {
            logcat("OkHttp") { it }
        }.also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    fun providePixabayApi(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): PixabayApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(com.example.data.BuildConfig.BACKEND_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePhotoDatabase(
        @ApplicationContext context: Context
    ): PixabayDatabase =
        Room.databaseBuilder(context, PixabayDatabase::class.java, "pixabay_db")
            .addMigrations()
            .build()
}
