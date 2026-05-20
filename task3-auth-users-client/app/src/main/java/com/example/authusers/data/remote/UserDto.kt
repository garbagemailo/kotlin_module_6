package com.example.authusers.data.remote

data class UserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val image: String
)
