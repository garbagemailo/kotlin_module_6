package com.example.nobelpublic.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nobelpublic.domain.model.LaureateListItem
import com.example.nobelpublic.presentation.detail.LaureateDetailScreen
import com.example.nobelpublic.presentation.list.LaureatesScreen
import kotlinx.serialization.json.Json

@Composable
fun NobelNavHost() {
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
        ) { backStackEntry ->
            val encoded = backStackEntry.arguments?.getString("item").orEmpty()
            val item = Json.decodeFromString(LaureateListItem.serializer(), Uri.decode(encoded))
            LaureateDetailScreen(
                item = item,
                viewModel = hiltViewModel(),
                onBack = { navController.popBackStack() }
            )
        }
    }
}
