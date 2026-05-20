package com.example.photocatalog.domain.repository

import android.content.ContentResolver
import android.net.Uri
import com.example.photocatalog.domain.model.Photo

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>
    suspend fun savePhotoToUri(downloadUrl: String, resolver: ContentResolver, targetUri: Uri)
}
