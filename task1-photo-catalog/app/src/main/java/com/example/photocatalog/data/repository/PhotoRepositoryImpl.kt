package com.example.photocatalog.data.repository

import android.content.ContentResolver
import android.net.Uri
import com.example.photocatalog.data.remote.PicsumApi
import com.example.photocatalog.domain.model.Photo
import com.example.photocatalog.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: PicsumApi
) : PhotoRepository {

    override suspend fun getPhotos(): List<Photo> = api.getPhotos().map { dto ->
        Photo(
            id = dto.id,
            author = dto.author,
            width = dto.width,
            height = dto.height,
            url = dto.url,
            downloadUrl = dto.download_url,
            thumbnailUrl = "https://picsum.photos/id/${dto.id}/400/400"
        )
    }

    override suspend fun savePhotoToUri(
        downloadUrl: String,
        resolver: ContentResolver,
        targetUri: Uri
    ) {
        withContext(Dispatchers.IO) {
            val outputStream = resolver.openOutputStream(targetUri)
                ?: error("Не удалось открыть поток записи")

            URL(downloadUrl).openStream().use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
        }
    }
}
