package com.example.nobelmemory.domain

interface PrizeRepository {
    suspend fun getAll(): List<NobelPrize>
    suspend fun getByYearAndCategory(year: Int, category: String): NobelPrize?
}
