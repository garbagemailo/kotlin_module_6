package com.example.photocatalog.domain.usecase

import com.example.photocatalog.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke() = repository.getPhotos()
}
