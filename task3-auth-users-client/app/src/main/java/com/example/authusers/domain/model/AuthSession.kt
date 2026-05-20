package com.example.authusers.domain.model

data class AuthSession(
    val token: String,
    val fullName: String,
    val email: String,
    val image: String
)
