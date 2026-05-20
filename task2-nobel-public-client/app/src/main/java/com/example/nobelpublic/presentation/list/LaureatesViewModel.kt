package com.example.nobelpublic.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nobelpublic.domain.model.LaureateListItem
import com.example.nobelpublic.domain.usecase.GetLaureatesUseCase
import com.example.nobelpublic.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaureatesViewModel @Inject constructor(
    private val getLaureatesUseCase: GetLaureatesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<LaureateListItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<LaureateListItem>>> = _uiState.asStateFlow()

    var yearFilter: String = ""
    var categoryFilter: String = ""

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                UiState.Success(
                    getLaureatesUseCase(
                        year = yearFilter.toIntOrNull(),
                        category = categoryFilter.ifBlank { null }
                    )
                )
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Ошибка загрузки лауреатов")
            }
        }
    }
}
