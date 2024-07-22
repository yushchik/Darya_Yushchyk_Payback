package com.example.data.file

interface FilesDataSource {
    suspend fun deleteFiles()
    fun getImageFilenamesWithoutExtension(directory: String): List<String>
    fun getImageFilenamesWithExtension(directory: String): List<String>
}
