package com.example.nobelpublic.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

class NobelApiService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getPrizes(year: Int?, category: String?): JsonObject = client.get("https://api.nobelprize.org/2.1/nobelPrizes") {
        parameter("limit", 25)
        parameter("offset", 0)
        year?.let { parameter("nobelPrizeYear", it) }
        category?.takeIf { it.isNotBlank() }?.let { parameter("nobelPrizeCategory", it) }
    }.body()

    suspend fun getLaureatesForPrize(year: Int, category: String): JsonObject = client.get("https://api.nobelprize.org/2.1/laureates") {
        parameter("limit", 100)
        parameter("offset", 0)
        parameter("nobelPrizeYear", year)
        parameter("nobelPrizeCategory", category)
    }.body()
}
