package com.example.data.source

import com.example.data.api.PixabayApi
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    private val pixabayApi: PixabayApi
) : ApiDataSource {
    override suspend fun getPhoto(query: String) = pixabayApi.getPhoto(query = query)
}
