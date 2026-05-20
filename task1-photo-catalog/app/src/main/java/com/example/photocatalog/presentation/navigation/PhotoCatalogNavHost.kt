package com.example.photocatalog.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.photocatalog.domain.model.Photo
import com.example.photocatalog.presentation.detail.PhotoDetailScreen
import com.example.photocatalog.presentation.list.PhotosScreen
import kotlinx.serialization.json.Json

@Composable
fun PhotoCatalogNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LIST) {
        composable(Routes.LIST) {
            PhotosScreen(
                viewModel = hiltViewModel(),
                onPhotoClick = { photo ->
                    val encoded = Uri.encode(Json.encodeToString(Photo.serializer(), photo))
                    navController.navigate(Routes.detail(encoded))
                }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("photoJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val photoJson = backStackEntry.arguments?.getString("photoJson").orEmpty()
            val photo = Json.decodeFromString(Photo.serializer(), Uri.decode(photoJson))
            PhotoDetailScreen(
                photo = photo,
                viewModel = hiltViewModel(),
                onBack = { navController.popBackStack() }
            )
        }
    }
}
