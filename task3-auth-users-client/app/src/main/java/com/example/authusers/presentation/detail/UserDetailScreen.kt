@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.authusers.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.authusers.presentation.common.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    userId: Int,
    viewModel: UserDetailViewModel,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.load(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали пользователя") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Назад") } }
            )
        }
    ) { padding ->
        when (val current = state) {
            UiState.Loading -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
            is UiState.Error -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(current.message)
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { viewModel.load(userId) }) { Text("Повторить") }
                }
            }
            is UiState.Success -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AsyncImage(
                    model = current.data.image,
                    contentDescription = current.data.username,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentScale = ContentScale.Crop
                )
                Text("Имя: ${current.data.firstName}", style = MaterialTheme.typography.titleMedium)
                Text("Фамилия: ${current.data.lastName}")
                Text("Email: ${current.data.email}")
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = { viewModel.logout(onLogout) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Выйти")
                }
            }
        }
    }
}
