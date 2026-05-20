package com.example.nobelpostgres.domain

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class LoginResponse(val token: String)

data class DbUser(
    val id: Int,
    val username: String,
    val passwordHash: String,
    val role: String
)

@Serializable
data class UserProfile(
    val id: Int,
    val username: String,
    val role: String
)

@Serializable
data class LaureateDto(
    val id: Int,
    val externalId: String?,
    val fullName: String,
    val portion: String?,
    val motivation: String?,
    val birthCountry: String?,
    val birthPlace: String?,
    val portraitUrl: String?
)

@Serializable
data class NobelPrizeDto(
    val id: Int,
    val awardYear: Int,
    val category: String,
    val categoryTitle: String,
    val topMotivation: String?,
    val detailLink: String?,
    val laureates: List<LaureateDto>
)

data class RemoteLaureateRecord(
    val externalId: String?,
    val fullName: String,
    val portion: String?,
    val motivation: String?,
    val birthCountry: String?,
    val birthPlace: String?,
    val portraitUrl: String?
)

data class RemotePrizeRecord(
    val year: Int,
    val category: String,
    val categoryTitle: String,
    val topMotivation: String?,
    val detailLink: String?,
    val laureates: List<RemoteLaureateRecord>
)
