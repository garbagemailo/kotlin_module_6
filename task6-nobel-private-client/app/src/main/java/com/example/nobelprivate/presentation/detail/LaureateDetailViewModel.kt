package com.example.nobelprivate.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobelprivate.domain.model.LaureateDetail
import com.example.nobelprivate.domain.model.LaureateListItem
import com.example.nobelprivate.domain.usecase.GetLaureateDetailUseCase
import com.example.nobelprivate.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaureateDetailViewModel @Inject constructor(
    private val getLaureateDetailUseCase: GetLaureateDetailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<LaureateDetail>>(UiState.Loading)
    val uiState: StateFlow<UiState<LaureateDetail>> = _uiState.asStateFlow()

    fun load(item: LaureateListItem) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                UiState.Success(getLaureateDetailUseCase(item))
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Ошибка загрузки деталей")
            }
        }
    }
}
