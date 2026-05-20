@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.authusers.presentation.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.authusers.domain.model.User
import com.example.authusers.presentation.common.UiState

@Composable
fun UsersScreen(
    viewModel: UsersViewModel,
    onUserClick: (User) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(topBar = { TopAppBar(title = { Text("Пользователи") }) }) { padding ->
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
                    Button(onClick = viewModel::loadUsers) { Text("Повторить") }
                }
            }

            is UiState.Success -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(current.data, key = { it.id }) { user ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clickable { onUserClick(user) }
                    ) {
                        Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            AsyncImage(
                                model = user.image,
                                contentDescription = user.username,
                                modifier = Modifier.height(72.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column {
                                Text("${user.firstName} ${user.lastName}", style = MaterialTheme.typography.titleMedium)
                                Text(user.username)
                                Text(user.email)
                            }
                        }
                    }
                }
            }
        }
    }
}
