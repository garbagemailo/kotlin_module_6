package com.example.authusers.data.remote

data class LoginRequestDto(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30
)
