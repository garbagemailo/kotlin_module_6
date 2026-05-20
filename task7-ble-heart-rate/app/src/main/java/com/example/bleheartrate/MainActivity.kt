@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.bleheartrate

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: HeartRateViewModel = viewModel(factory = HeartRateViewModel.factory(application))
            val permissions = remember {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT)
                } else {
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { }
            LaunchedEffect(Unit) { launcher.launch(permissions) }
            HeartRateScreen(vm)
        }
    }
}

@Composable
fun HeartRateScreen(viewModel: HeartRateViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("BLE монитор сердечного ритма") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Heart Rate: ${state.heartRate?.let { "$it bpm" } ?: "—"}", style = MaterialTheme.typography.headlineSmall)
            Text("Статус: ${state.status}")
            Button(
                onClick = { if (state.isScanning) viewModel.stopScan() else viewModel.startScan() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (state.isScanning) "Остановить сканирование" else "Начать сканирование")
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.devices, key = { it.address }) { device ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(device.name.ifBlank { "Unknown BLE device" }, style = MaterialTheme.typography.titleMedium)
                            Text(device.address)
                            Button(onClick = { viewModel.connect(device.address) }) {
                                Text("Подключиться")
                            }
                        }
                    }
                }
            }
        }
    }
}
