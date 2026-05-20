@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.nobelprivate.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.nobelprivate.domain.model.LaureateListItem
import com.example.nobelprivate.presentation.common.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaureateDetailScreen(
    item: LaureateListItem,
    viewModel: LaureateDetailViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(item.id) {
        viewModel.load(item)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали лауреата") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Назад") } }
            )
        }
    ) { padding ->
        when (val current = state) {
            UiState.Loading -> Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            is UiState.Error -> Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(current.message)
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { viewModel.load(item) }) { Text("Повторить") }
                }
            }
            is UiState.Success -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                current.data.portraitUrl?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = current.data.fullName,
                        modifier = Modifier.fillMaxWidth().height(280.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(current.data.fullName, style = MaterialTheme.typography.headlineSmall)
                Text("Год: ${current.data.year}")
                Text("Категория: ${current.data.category}")
                Text("Описание: ${current.data.motivation}")
                Text("Страна: ${current.data.birthCountry ?: "Нет данных"}")
                Text("Место рождения: ${current.data.birthPlace ?: "Нет данных"}")
            }
        }
    }
}
