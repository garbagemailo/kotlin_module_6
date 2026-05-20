package com.example.photocatalog.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photocatalog.domain.model.Photo
import com.example.photocatalog.domain.usecase.GetPhotosUseCase
import com.example.photocatalog.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Photo>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Photo>>> = _uiState.asStateFlow()

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                UiState.Success(getPhotosUseCase())
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Не удалось загрузить фотографии")
            }
        }
    }
}
