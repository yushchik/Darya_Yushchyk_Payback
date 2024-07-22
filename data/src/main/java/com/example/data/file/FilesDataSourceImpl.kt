package com.example.data.file

import android.content.Context
import com.example.data.Constants.FULL_PHOTO_FOLDER_NAME
import com.example.data.Constants.PHOTO_PREVIEW_FOLDER_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import logcat.logcat
import java.io.File
import javax.inject.Inject

class FilesDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FilesDataSource {

    private val fileDir: File by lazy {
        context.filesDir
    }

    override suspend fun deleteFiles() {
        File(fileDir, PHOTO_PREVIEW_FOLDER_NAME).delete()
        File(fileDir, FULL_PHOTO_FOLDER_NAME).delete()
    }

    override fun getImageFilenamesWithExtension(directory: String): List<String> {
        return File(fileDir, directory).listFiles()
            ?.map { it.name }
            .orEmpty()
    }

    override fun getImageFilenamesWithoutExtension(directory: String): List<String> {
        return File(fileDir, directory).listFiles()
            ?.map { it.nameWithoutExtension }
            .orEmpty()
    }
}