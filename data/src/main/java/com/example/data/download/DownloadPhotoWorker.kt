package com.example.data.download

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import logcat.logcat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.URL

class DownloadPhotoWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val folderName =
            inputData.getString(WORKER_INPUT_KEY_PICTURE_FOLDER) ?: return Result.failure()
        val fileName =
            inputData.getString(WORKER_INPUT_KEY_PICTURE_FILENAME) ?: return Result.failure()
        val pictureUrl = inputData.getString(WORKER_INPUT_KEY_PICTURE) ?: return Result.failure()

        if (fileName.exists()) {
            return Result.success()
        }

        downloadImage(pictureUrl, fileName)?.let { inputStream ->
            saveImage(inputStream, fileName, folderName)
        }

        return Result.success()
    }

    private fun downloadImage(fullUrl: String, filename: String): InputStream? = try {
        URL(fullUrl).openConnection().getInputStream()
    } catch (e: IOException) {
        logcat(TAG) {
            "$e, Failed to Download Image! Please try again later.\nFile for picture `$filename`\nwas not created.\n"
        }
        null
    }


    private val fileDir: File by lazy {
        context.filesDir
    }

    private fun saveImage(inputStream: InputStream, imageFileName: String, folderName: String) {
        val imageFolder = File(fileDir, folderName)
        if (!imageFolder.exists()) {
            imageFolder.mkdirs()
        }
        val imageFile = File(imageFolder, imageFileName)
        val savedImagePath: String = imageFile.absolutePath

        try {
            val outputStream: OutputStream = FileOutputStream(imageFile)
            inputStream.copyTo(outputStream)
            outputStream.close()
        } catch (e: Exception) {
            logcat {  "$e, Error while saving image! savedImagePath = $savedImagePath" }
        }
    }

    private fun String.exists(): Boolean {
        val imageFile = File(fileDir, this)
        return imageFile.exists()
    }

    companion object {
        private val TAG = DownloadPhotoWorker::class.java.simpleName
        const val WORKER_INPUT_KEY_PICTURE_FOLDER = "picture_folder"
        const val WORKER_INPUT_KEY_PICTURE_FILENAME = "picture_filename"
        const val WORKER_INPUT_KEY_PICTURE = "picture"
    }
}