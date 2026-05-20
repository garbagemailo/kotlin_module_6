package com.example.authusers.presentation.navigation

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val USERS = "users"
    const val DETAIL = "detail/{userId}"
    fun detail(userId: Int) = "detail/$userId"
}
