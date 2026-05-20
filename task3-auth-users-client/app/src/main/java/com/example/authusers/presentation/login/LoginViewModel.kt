package com.example.authusers.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authusers.domain.usecase.LoginUseCase
import com.example.authusers.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Success(Unit))
    val uiState: StateFlow<UiState<Unit>> = _uiState.asStateFlow()

    fun login(username: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                loginUseCase(username, password)
                onSuccess()
                UiState.Success(Unit)
            } catch (e: HttpException) {
                UiState.Error("Неверные данные")
            } catch (e: IOException) {
                UiState.Error("Нет соединения")
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Ошибка авторизации")
            }
        }
    }
}
