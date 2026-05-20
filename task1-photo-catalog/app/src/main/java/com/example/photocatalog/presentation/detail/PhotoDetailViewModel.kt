package com.example.photocatalog.presentation.detail

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photocatalog.domain.usecase.SavePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val savePhotoUseCase: SavePhotoUseCase
) : ViewModel() {

    private val _downloadState = MutableStateFlow<String?>(null)
    val downloadState: StateFlow<String?> = _downloadState.asStateFlow()

    fun savePhoto(downloadUrl: String, resolver: ContentResolver, targetUri: Uri) {
        viewModelScope.launch {
            _downloadState.value = "Скачивание..."
            _downloadState.value = try {
                savePhotoUseCase(downloadUrl, resolver, targetUri)
                "Фото успешно сохранено"
            } catch (e: Exception) {
                e.message ?: "Не удалось сохранить файл"
            }
        }
    }

    fun clearMessage() {
        _downloadState.value = null
    }
}
