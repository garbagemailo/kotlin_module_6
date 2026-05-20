package com.example.authusers.data.remote

data class LoginResponseDto(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val accessToken: String? = null,
    val token: String? = null
)
