package com.example.nobelpublic.data.repository

import com.example.nobelpublic.data.remote.NobelApiService
import com.example.nobelpublic.data.remote.arr
import com.example.nobelpublic.data.remote.asObjectOrNull
import com.example.nobelpublic.data.remote.obj
import com.example.nobelpublic.data.remote.string
import com.example.nobelpublic.data.remote.textEn
import com.example.nobelpublic.domain.model.LaureateDetail
import com.example.nobelpublic.domain.model.LaureateListItem
import com.example.nobelpublic.domain.repository.NobelRepository
import javax.inject.Inject

class NobelRepositoryImpl @Inject constructor(
    private val api: NobelApiService
) : NobelRepository {

    override suspend fun getLaureates(year: Int?, category: String?): List<LaureateListItem> {
        val response = api.getPrizes(year, category)
        return response.arr("nobelPrizes").mapNotNull { prizeElement ->
            prizeElement.asObjectOrNull()
        }.flatMap { prize ->
            val awardYear = prize.string("awardYear")?.toIntOrNull() ?: return@flatMap emptyList()
            val prizeCategory = prize.textEn("category")?.lowercase() ?: return@flatMap emptyList()
            prize.arr("laureates").mapNotNull { laureateElement ->
                val laureate = laureateElement.asObjectOrNull() ?: return@mapNotNull null
                val motivation = laureate.textEn("motivation") ?: prize.textEn("topMotivation") ?: "Описание отсутствует"
                LaureateListItem(
                    id = laureate.string("id") ?: return@mapNotNull null,
                    year = awardYear,
                    category = prizeCategory,
                    fullName = laureate.textEn("fullName")
                        ?: laureate.textEn("knownName")
                        ?: laureate.textEn("orgName")
                        ?: "Unknown laureate",
                    motivationShort = motivation.take(100),
                    motivationFull = motivation
                )
            }
        }
    }

    override suspend fun getLaureateDetail(item: LaureateListItem): LaureateDetail {
        val response = api.getLaureatesForPrize(item.year, item.category)
        val laureate = response.arr("laureates")
            .mapNotNull { it.asObjectOrNull() }
            .firstOrNull { it.string("id") == item.id }

        if (laureate == null) {
            return LaureateDetail(
                id = item.id,
                fullName = item.fullName,
                year = item.year,
                category = item.category,
                motivation = item.motivationFull,
                birthCountry = null,
                birthPlace = null,
                portraitUrl = null
            )
        }

        val birth = laureate.obj("birth")
        val place = birth?.obj("place")
        val portraitUrl = laureate.string("portraitUrl")
            ?: laureate.string("image")
            ?: laureate.obj("wikipedia")?.string("image")
            ?: laureate.arr("links").mapNotNull { it.asObjectOrNull()?.string("href") ?: it.asObjectOrNull()?.string("url") }
                .firstOrNull { it.endsWith(".jpg") || it.endsWith(".png") || it.endsWith(".jpeg") }

        return LaureateDetail(
            id = item.id,
            fullName = laureate.textEn("fullName")
                ?: laureate.textEn("knownName")
                ?: laureate.textEn("orgName")
                ?: item.fullName,
            year = item.year,
            category = item.category,
            motivation = laureate.arr("nobelPrizes")
                .mapNotNull { it.asObjectOrNull() }
                .firstOrNull { prize ->
                    prize.string("awardYear") == item.year.toString() &&
                        prize.textEn("category")?.lowercase() == item.category
                }?.textEn("motivation")
                ?: item.motivationFull,
            birthCountry = place?.obj("country")?.string("en") ?: place?.textEn("country"),
            birthPlace = place?.obj("city")?.string("en") ?: place?.textEn("city"),
            portraitUrl = portraitUrl
        )
    }
}
