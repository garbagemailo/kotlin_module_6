package com.example.nobelprivate.data.remote

import com.example.nobelprivate.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.Serializable
import javax.inject.Inject

private val BASE_URL = BuildConfig.BASE_URL.trimEnd('/')

class PrivateNobelApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getPrizes(year: Int?, category: String?): List<PrizeDto> = client.get("$BASE_URL/prizes") {
        year?.let { parameter("year", it) }
        category?.takeIf { it.isNotBlank() }?.let { parameter("category", it) }
    }.body()

    suspend fun getLaureates(year: Int, category: String): List<LaureateDto> =
        client.get("$BASE_URL/prizes/$year/$category/laureates").body()
}

@Serializable
data class PrizeDto(
    val id: Int,
    val awardYear: Int,
    val category: String,
    val categoryTitle: String,
    val topMotivation: String? = null,
    val detailLink: String? = null,
    val laureates: List<LaureateDto>
)

@Serializable
data class LaureateDto(
    val id: Int,
    val externalId: String? = null,
    val fullName: String,
    val portion: String? = null,
    val motivation: String? = null,
    val birthCountry: String? = null,
    val birthPlace: String? = null,
    val portraitUrl: String? = null
)
