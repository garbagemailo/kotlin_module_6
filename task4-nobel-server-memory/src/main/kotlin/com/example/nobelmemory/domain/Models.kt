package com.example.nobelmemory.domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class LoginResponse(val token: String, val expiresInMinutes: Int = 30)

@Serializable
data class Laureate(
    val id: String,
    val fullName: String,
    val portion: String,
    val motivation: String,
    val birthCountryOrPlace: String
)

@Serializable
data class NobelPrize(
    val year: Int,
    val category: String,
    val categoryTitle: String,
    val topMotivation: String,
    val laureates: List<Laureate>
)
