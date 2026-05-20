package com.example.authusers.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.authusers.presentation.detail.UserDetailScreen
import com.example.authusers.presentation.login.LoginScreen
import com.example.authusers.presentation.splash.SplashScreen
import com.example.authusers.presentation.users.UsersScreen

@Composable
fun AuthUsersNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(
                viewModel = hiltViewModel(),
                onAuthorized = {
                    navController.navigate(Routes.USERS) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                },
                onUnauthorized = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = hiltViewModel(),
                onLoginSuccess = {
                    navController.navigate(Routes.USERS) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.USERS) {
            UsersScreen(
                viewModel = hiltViewModel(),
                onUserClick = { user -> navController.navigate(Routes.detail(user.id)) }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            UserDetailScreen(
                userId = userId,
                viewModel = hiltViewModel(),
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.USERS) { inclusive = true }
                    }
                }
            )
        }
    }
}
