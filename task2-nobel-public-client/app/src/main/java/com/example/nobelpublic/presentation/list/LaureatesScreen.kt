@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.nobelpublic.presentation.list

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.nobelpublic.domain.model.LaureateListItem
import com.example.nobelpublic.presentation.common.UiState

private val categories = listOf("", "physics", "chemistry", "literature", "peace", "medicine", "economics")

@Composable
fun LaureatesScreen(
    viewModel: LaureatesViewModel,
    onItemClick: (LaureateListItem) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    var dropdownExpanded by remember { mutableStateOf(false) }
    var year by remember { mutableStateOf(viewModel.yearFilter) }
    var category by remember { mutableStateOf(viewModel.categoryFilter) }

    Scaffold(topBar = { TopAppBar(title = { Text("Нобелевские лауреаты") }) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = year,
                onValueChange = { year = it.filter(Char::isDigit) },
                label = { Text("Год") },
                modifier = Modifier.fillMaxWidth()
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { dropdownExpanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Категория: ${category.ifBlank { "Все категории" }}")
                }
                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false }
                ) {
                    categories.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(if (item.isBlank()) "Все категории" else item) },
                            onClick = {
                                category = item
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }
            Button(
                onClick = {
                    viewModel.yearFilter = year
                    viewModel.categoryFilter = category
                    viewModel.load()
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Применить фильтр") }

            when (val current = state) {
                UiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                is UiState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(current.message)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = viewModel::load) { Text("Повторить") }
                    }
                }
                is UiState.Success -> LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(current.data, key = { it.id + it.year + it.category }) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onItemClick(item) }
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text("${item.year} · ${item.category}", style = MaterialTheme.typography.labelLarge)
                                Text(item.fullName, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    text = item.motivationShort,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
