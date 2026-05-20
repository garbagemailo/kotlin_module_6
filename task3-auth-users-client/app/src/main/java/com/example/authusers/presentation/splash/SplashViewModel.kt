package com.example.authusers.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authusers.domain.usecase.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _hasToken = MutableStateFlow<Boolean?>(null)
    val hasToken: StateFlow<Boolean?> = _hasToken.asStateFlow()

    init {
        viewModelScope.launch {
            getTokenUseCase().collect { token ->
                _hasToken.value = !token.isNullOrBlank()
            }
        }
    }
}
