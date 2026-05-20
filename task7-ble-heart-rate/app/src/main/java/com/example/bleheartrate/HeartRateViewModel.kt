package com.example.bleheartrate

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeartRateViewModel(application: Application) : AndroidViewModel(application) {
    private val manager = HeartRateBleManager(application)

    private val _uiState = MutableStateFlow(HeartRateUiState())
    val uiState: StateFlow<HeartRateUiState> = _uiState.asStateFlow()

    init {
        manager.onDevicesChanged = { devices ->
            _uiState.value = _uiState.value.copy(devices = devices)
        }
        manager.onStatusChanged = { status ->
            _uiState.value = _uiState.value.copy(status = status)
        }
        manager.onHeartRateChanged = { bpm ->
            _uiState.value = _uiState.value.copy(heartRate = bpm, status = "Получаем уведомления")
        }
    }

    fun startScan() {
        _uiState.value = _uiState.value.copy(isScanning = true, status = "Сканирование...")
        manager.startScan()
    }

    fun stopScan() {
        _uiState.value = _uiState.value.copy(isScanning = false, status = "Сканирование остановлено")
        manager.stopScan()
    }

    fun connect(address: String) {
        _uiState.value = _uiState.value.copy(status = "Подключение к устройству...")
        manager.connect(address)
    }

    override fun onCleared() {
        super.onCleared()
        manager.close()
    }

    companion object {
        fun factory(application: Application) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = HeartRateViewModel(application) as T
        }
    }
}

data class HeartRateUiState(
    val devices: List<BleDeviceUi> = emptyList(),
    val heartRate: Int? = null,
    val status: String = "Готово к сканированию",
    val isScanning: Boolean = false
)

data class BleDeviceUi(
    val name: String,
    val address: String
)
