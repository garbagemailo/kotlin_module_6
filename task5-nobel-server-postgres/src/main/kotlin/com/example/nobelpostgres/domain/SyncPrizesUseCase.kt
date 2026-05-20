package com.example.nobelpostgres.domain

import com.example.nobelpostgres.data.remote.NobelRemoteDataSource
import com.example.nobelpostgres.data.remote.arr
import com.example.nobelpostgres.data.remote.obj
import com.example.nobelpostgres.data.remote.objOrNull
import com.example.nobelpostgres.data.remote.string
import com.example.nobelpostgres.data.remote.textEn
import com.example.nobelpostgres.data.repository.PrizeRepositoryImpl
import org.slf4j.LoggerFactory

class SyncPrizesUseCase(
    private val prizeRepository: PrizeRepositoryImpl,
    private val remoteDataSource: NobelRemoteDataSource
) {
    private val logger = LoggerFactory.getLogger(SyncPrizesUseCase::class.java)

    suspend operator fun invoke(force: Boolean = false): Boolean {
        if (!force && !prizeRepository.needsRefresh()) return false

        return runCatching {
            val laureateProfiles = remoteDataSource.fetchLaureates()
            .arr("laureates")
            .mapNotNull { element -> element.objOrNull() }
            .associateBy(
                keySelector = { it.string("id") ?: "" },
                valueTransform = { laureate ->
                    val birth = laureate.obj("birth")
                    val place = birth?.obj("place")
                    RemoteLaureateRecord(
                        externalId = laureate.string("id"),
                        fullName = laureate.textEn("fullName") ?: laureate.textEn("knownName") ?: laureate.textEn("orgName") ?: "Unknown laureate",
                        portion = null,
                        motivation = null,
                        birthCountry = place?.obj("country")?.string("en") ?: place?.textEn("country"),
                        birthPlace = place?.obj("city")?.string("en") ?: place?.textEn("city"),
                        portraitUrl = laureate.string("portraitUrl")
                            ?: laureate.string("image")
                            ?: laureate.obj("wikipedia")?.string("image")
                    )
                }
            )

            val prizes = remoteDataSource.fetchPrizes()
            .arr("nobelPrizes")
            .mapNotNull { it.objOrNull() }
            .mapNotNull { prize ->
                val year = prize.string("awardYear")?.toIntOrNull() ?: return@mapNotNull null
                val category = prize.textEn("category")?.lowercase() ?: return@mapNotNull null
                RemotePrizeRecord(
                    year = year,
                    category = category,
                    categoryTitle = prize.textEn("category") ?: category,
                    topMotivation = prize.textEn("topMotivation"),
                    detailLink = prize.arr("links").firstOrNull()?.objOrNull()?.string("href"),
                    laureates = prize.arr("laureates").mapNotNull { it.objOrNull() }.map { laureate ->
                        val externalId = laureate.string("id")
                        val profile = laureateProfiles[externalId]
                        RemoteLaureateRecord(
                            externalId = externalId,
                            fullName = laureate.textEn("fullName") ?: laureate.textEn("knownName") ?: laureate.textEn("orgName") ?: "Unknown laureate",
                            portion = laureate.string("portion"),
                            motivation = laureate.textEn("motivation") ?: prize.textEn("topMotivation"),
                            birthCountry = profile?.birthCountry,
                            birthPlace = profile?.birthPlace,
                            portraitUrl = profile?.portraitUrl
                        )
                    }
                )
            }
            if (prizes.isEmpty()) error("Nobel Prize API returned an empty prize list")
            prizeRepository.replaceAll(prizes)
            true
        }.getOrElse { error ->
            logger.warn("Nobel API sync failed; keeping existing local data", error)
            prizeRepository.ensureSampleDataIfEmpty()
            false
        }
    }
}

