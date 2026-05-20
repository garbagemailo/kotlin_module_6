package com.example.nobelpostgres.data.repository

import com.example.nobelpostgres.data.SeedData
import com.example.nobelpostgres.data.db.LaureatesTable
import com.example.nobelpostgres.data.db.PrizesTable
import com.example.nobelpostgres.data.db.UserPrizesTable
import com.example.nobelpostgres.domain.LaureateDto
import com.example.nobelpostgres.domain.NobelPrizeDto
import com.example.nobelpostgres.domain.RemoteLaureateRecord
import com.example.nobelpostgres.domain.RemotePrizeRecord
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class PrizeRepositoryImpl {
    fun needsRefresh(maxAgeHours: Long = 24): Boolean = transaction {
        val newest = PrizesTable.selectAll().maxOfOrNull { it[PrizesTable.updatedAt] }
        newest == null || newest.isBefore(LocalDateTime.now().minusHours(maxAgeHours))
    }

    fun hasPrizes(): Boolean = transaction {
        PrizesTable.selectAll().count() > 0L
    }

    fun ensureSampleDataIfEmpty() {
        if (!hasPrizes()) {
            replaceAll(SeedData.prizes)
        }
    }

    fun replaceAll(prizes: List<RemotePrizeRecord>) = transaction {
        UserPrizesTable.deleteAll()
        LaureatesTable.deleteAll()
        PrizesTable.deleteAll()

        prizes.forEach { prize ->
            val prizeId = PrizesTable.insert {
                it[awardYear] = prize.year
                it[category] = prize.category
                it[categoryTitle] = prize.categoryTitle
                it[topMotivation] = prize.topMotivation
                it[detailLink] = prize.detailLink
                it[updatedAt] = LocalDateTime.now()
            }[PrizesTable.id]

            prize.laureates.forEach { laureate ->
                LaureatesTable.insert {
                    it[externalId] = laureate.externalId
                    it[LaureatesTable.prizeId] = prizeId
                    it[fullName] = laureate.fullName
                    it[portion] = laureate.portion
                    it[motivation] = laureate.motivation
                    it[birthCountry] = laureate.birthCountry
                    it[birthPlace] = laureate.birthPlace
                    it[portraitUrl] = laureate.portraitUrl
                }
            }
        }
    }

    fun getPrizes(year: Int?, category: String?): List<NobelPrizeDto> = transaction {
        val query = PrizesTable.selectAll().apply {
            year?.let { andWhere { PrizesTable.awardYear eq it } }
            category?.takeIf { it.isNotBlank() }?.let { andWhere { PrizesTable.category eq it } }
        }
        query.map { row -> row.toPrizeDto(fetchLaureates(row[PrizesTable.id])) }
    }

    fun getPrizeByKey(year: Int, category: String): NobelPrizeDto? = transaction {
        PrizesTable.selectAll().where { (PrizesTable.awardYear eq year) and (PrizesTable.category eq category) }
            .singleOrNull()
            ?.let { row -> row.toPrizeDto(fetchLaureates(row[PrizesTable.id])) }
    }

    fun getLaureates(year: Int, category: String): List<LaureateDto> = getPrizeByKey(year, category)?.laureates ?: emptyList()

    fun addFavorite(userId: Int, prizeId: Int) = transaction {
        UserPrizesTable.insertIgnore {
            it[UserPrizesTable.userId] = userId
            it[UserPrizesTable.prizeId] = prizeId
            it[addedAt] = LocalDateTime.now()
        }
    }

    fun removeFavorite(userId: Int, prizeId: Int) = transaction {
        UserPrizesTable.deleteWhere { (UserPrizesTable.userId eq userId) and (UserPrizesTable.prizeId eq prizeId) }
    }

    fun getFavorites(userId: Int): List<NobelPrizeDto> = transaction {
        val prizeIds = UserPrizesTable.selectAll().where { UserPrizesTable.userId eq userId }
            .map { it[UserPrizesTable.prizeId] }
        if (prizeIds.isEmpty()) emptyList()
        else PrizesTable.selectAll().where { PrizesTable.id inList prizeIds }
            .map { row -> row.toPrizeDto(fetchLaureates(row[PrizesTable.id])) }
    }

    private fun fetchLaureates(prizeId: Int): List<LaureateDto> = LaureatesTable.selectAll()
        .where { LaureatesTable.prizeId eq prizeId }
        .map {
            LaureateDto(
                id = it[LaureatesTable.id],
                externalId = it[LaureatesTable.externalId],
                fullName = it[LaureatesTable.fullName],
                portion = it[LaureatesTable.portion],
                motivation = it[LaureatesTable.motivation],
                birthCountry = it[LaureatesTable.birthCountry],
                birthPlace = it[LaureatesTable.birthPlace],
                portraitUrl = it[LaureatesTable.portraitUrl]
            )
        }

    private fun org.jetbrains.exposed.sql.ResultRow.toPrizeDto(laureates: List<LaureateDto>) = NobelPrizeDto(
        id = this[PrizesTable.id],
        awardYear = this[PrizesTable.awardYear],
        category = this[PrizesTable.category],
        categoryTitle = this[PrizesTable.categoryTitle],
        topMotivation = this[PrizesTable.topMotivation],
        detailLink = this[PrizesTable.detailLink],
        laureates = laureates
    )
}
