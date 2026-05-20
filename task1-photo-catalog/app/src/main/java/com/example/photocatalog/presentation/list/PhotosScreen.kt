@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.photocatalog.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photocatalog.domain.model.Photo
import com.example.photocatalog.presentation.common.UiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel,
    onPhotoClick: (Photo) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Фотокаталог") }) }
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
                    .padding(24.dp)
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(current.message)
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = viewModel::loadPhotos) { Text("Повторить") }
                }
            }

            is UiState.Success -> LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(current.data, key = { it.id }) { photo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement()
                            .clickable { onPhotoClick(photo) }
                    ) {
                        Column(Modifier.padding(8.dp)) {
                            AsyncImage(
                                model = photo.thumbnailUrl,
                                contentDescription = photo.author,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(photo.author, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(
                                text = "${photo.width} × ${photo.height}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
