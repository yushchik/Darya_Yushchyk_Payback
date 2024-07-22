package com.example.domain.photo

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.data.Constants.FULL_PHOTO_FOLDER_NAME
import com.example.data.Constants.PHOTO_PREVIEW_FOLDER_NAME
import com.example.data.download.DownloadPhotoWorker
import com.example.domain.model.PhotoModel
import dagger.hilt.android.qualifiers.ApplicationContext
import logcat.LogPriority
import logcat.logcat
import java.io.File
import javax.inject.Inject

class LoadPhotoImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LoadPhoto {

    override suspend fun execute(result: List<PhotoModel>) {
        launchAndHandle(suspend {
            loadPhoto(result)
        })
    }

    private fun loadPhoto(result: List<PhotoModel>) {
        val pictureWorkerList = mutableListOf<OneTimeWorkRequest>()
        result
            .forEach { dataItem ->
            if (dataItem.previewURL != null) {
                addWorkerRequest(
                    "${dataItem.id}.${dataItem.previewURL.split(".").last()}",
                    dataItem.previewURL,
                    PHOTO_PREVIEW_FOLDER_NAME,
                    pictureWorkerList
                )
            }
            if (dataItem.imageURL != null) {
                addWorkerRequest(
                    "${dataItem.id}.${dataItem.imageURL.split(".").last()}",
                    dataItem.imageURL,
                    FULL_PHOTO_FOLDER_NAME,
                    pictureWorkerList
                )
            }
        }

        workManager(pictureWorkerList)
    }

    private fun String.exists(): Boolean {
        val imageFile = File(fileDir, this)
        return imageFile.exists()
    }

    private val fileDir: File by lazy {
        context.filesDir
    }

    private fun addWorkerRequest(
        imageName: String,
        imageUrl: String,
        folderName: String,
        pictureWorkerList: MutableList<OneTimeWorkRequest>
    ) {
        val request = OneTimeWorkRequest.Builder(DownloadPhotoWorker::class.java)
            .setConstraints(constraints())
            .setInputData(createPictureData(imageName, imageUrl, folderName))
            .build()

        pictureWorkerList.add(request)
    }

    private fun workManager(pictureWorkerList: MutableList<OneTimeWorkRequest>) {
        val workManager = WorkManager.getInstance(context)
        workManager.enqueue(pictureWorkerList)
        val uuidList = pictureWorkerList.map { it.id }
        uuidList.map { uuid ->
            workManager.getWorkInfoByIdLiveData(uuid).asFlow()
        }
    }

    private fun createPictureData(imageName: String, imageUrl: String, folderName: String): Data {
        return Data.Builder()
            .putString(
                DownloadPhotoWorker.WORKER_INPUT_KEY_PICTURE_FILENAME,
                imageName
            )
            .putString(
                DownloadPhotoWorker.WORKER_INPUT_KEY_PICTURE,
                imageUrl
            )
            .putString(DownloadPhotoWorker.WORKER_INPUT_KEY_PICTURE_FOLDER, folderName)
            .build()
    }

    private fun constraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private suspend fun launchAndHandle(block: suspend () -> Unit) {
        try {
            block()
        } catch (e: Exception) {
            logcat("LoadImagesImpl", priority = LogPriority.ERROR) {
                "$e"
            }
        }
    }
}
