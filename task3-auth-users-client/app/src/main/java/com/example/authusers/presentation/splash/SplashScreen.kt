package com.example.authusers.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onAuthorized: () -> Unit,
    onUnauthorized: () -> Unit
) {
    val hasToken by viewModel.hasToken.collectAsState()

    LaunchedEffect(hasToken) {
        when (hasToken) {
            true -> onAuthorized()
            false -> onUnauthorized()
            null -> Unit
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
