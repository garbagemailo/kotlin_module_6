package com.example.nobelprivate.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nobelprivate.domain.model.LaureateListItem
import com.example.nobelprivate.presentation.detail.LaureateDetailScreen
import com.example.nobelprivate.presentation.list.LaureatesScreen
import kotlinx.serialization.json.Json

@Composable
fun NobelPrivateNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LIST) {
        composable(Routes.LIST) {
            LaureatesScreen(
                viewModel = hiltViewModel(),
                onItemClick = { item ->
                    val encoded = Uri.encode(Json.encodeToString(LaureateListItem.serializer(), item))
                    navController.navigate(Routes.detail(encoded))
                }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("item") { type = NavType.StringType })
        ) { entry ->
            val item = Json.decodeFromString(
                LaureateListItem.serializer(),
                Uri.decode(entry.arguments?.getString("item").orEmpty())
            )
            LaureateDetailScreen(item = item, viewModel = hiltViewModel(), onBack = { navController.popBackStack() })
        }
    }
}
