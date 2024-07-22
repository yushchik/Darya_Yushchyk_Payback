package com.example.data.api

import com.example.data.BuildConfig
import com.example.data.entities.api.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi{
    @GET("api")
    suspend fun getPhoto(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") query: String
    ): PhotoResponse
}
