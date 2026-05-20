package com.example.photocatalog.domain.usecase

import android.content.ContentResolver
import android.net.Uri
import com.example.photocatalog.domain.repository.PhotoRepository
import javax.inject.Inject

class SavePhotoUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(downloadUrl: String, resolver: ContentResolver, uri: Uri) {
        repository.savePhotoToUri(downloadUrl, resolver, uri)
    }
}
