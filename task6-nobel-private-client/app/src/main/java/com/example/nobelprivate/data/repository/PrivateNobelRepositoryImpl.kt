package com.example.nobelprivate.data.repository

import com.example.nobelprivate.data.remote.PrivateNobelApi
import com.example.nobelprivate.domain.model.LaureateDetail
import com.example.nobelprivate.domain.model.LaureateListItem
import com.example.nobelprivate.domain.repository.PrivateNobelRepository
import javax.inject.Inject

class PrivateNobelRepositoryImpl @Inject constructor(
    private val api: PrivateNobelApi
) : PrivateNobelRepository {
    override suspend fun getLaureates(year: Int?, category: String?): List<LaureateListItem> {
        return api.getPrizes(year, category).flatMap { prize ->
            prize.laureates.map { laureate ->
                LaureateListItem(
                    id = laureate.id,
                    year = prize.awardYear,
                    category = prize.category,
                    fullName = laureate.fullName,
                    motivationShort = (laureate.motivation ?: prize.topMotivation.orEmpty()).take(100),
                    motivationFull = laureate.motivation ?: prize.topMotivation.orEmpty(),
                    portraitUrl = laureate.portraitUrl
                )
            }
        }
    }

    override suspend fun getLaureateDetail(item: LaureateListItem): LaureateDetail {
        val laureate = api.getLaureates(item.year, item.category).firstOrNull { it.id == item.id }
        return LaureateDetail(
            id = item.id,
            fullName = laureate?.fullName ?: item.fullName,
            year = item.year,
            category = item.category,
            motivation = laureate?.motivation ?: item.motivationFull,
            birthCountry = laureate?.birthCountry,
            birthPlace = laureate?.birthPlace,
            portraitUrl = laureate?.portraitUrl ?: item.portraitUrl
        )
    }
}
