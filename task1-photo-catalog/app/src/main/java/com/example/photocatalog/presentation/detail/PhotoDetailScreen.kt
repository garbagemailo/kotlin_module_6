@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.photocatalog.presentation.detail

import android.content.Intent
import android.provider.DocumentsContract
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.OpenDocumentTree
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.documentfile.provider.DocumentFile
import coil.compose.AsyncImage
import com.example.photocatalog.domain.model.Photo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailScreen(
    photo: Photo,
    viewModel: PhotoDetailViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val message by viewModel.downloadState.collectAsState()

    val treePicker = rememberLauncherForActivityResult(OpenDocumentTree()) { treeUri ->
        if (treeUri != null) {
            context.contentResolver.takePersistableUriPermission(
                treeUri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
            val directory = DocumentFile.fromTreeUri(context, treeUri)
            val fileName = "picsum_${photo.id}.jpg"
            val file = directory?.createFile("image/jpeg", fileName)
            if (file != null) {
                viewModel.savePhoto(photo.downloadUrl, context.contentResolver, file.uri)
            } else {
                Toast.makeText(context, "Не удалось создать файл", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearMessage()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали фото") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Назад") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = photo.downloadUrl,
                contentDescription = photo.author,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                contentScale = ContentScale.Crop
            )
            Text("Автор: ${photo.author}", style = MaterialTheme.typography.titleMedium)
            Text("Размер: ${photo.width} × ${photo.height}")
            Text("Ссылка: ${photo.url}")
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { treePicker.launch(null) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Скачать фото")
            }
            Text(
                text = "Для строгого выполнения SAF выберите папку Downloads в системном диалоге.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
