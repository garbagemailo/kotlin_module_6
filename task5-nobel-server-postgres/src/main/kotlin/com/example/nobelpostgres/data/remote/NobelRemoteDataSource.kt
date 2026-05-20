package com.example.nobelpostgres.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class NobelRemoteDataSource {
    private val client = HttpClient(CIO) {
        install(HttpTimeout) {
            connectTimeoutMillis = 3_000
            requestTimeoutMillis = 8_000
            socketTimeoutMillis = 8_000
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun fetchPrizes(): JsonObject = client.get("https://api.nobelprize.org/2.1/nobelPrizes") {
        parameter("limit", 1000)
        parameter("offset", 0)
    }.body()

    suspend fun fetchLaureates(): JsonObject = client.get("https://api.nobelprize.org/2.1/laureates") {
        parameter("limit", 1000)
        parameter("offset", 0)
    }.body()
}
