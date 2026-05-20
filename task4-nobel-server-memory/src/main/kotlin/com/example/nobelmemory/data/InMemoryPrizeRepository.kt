package com.example.nobelmemory.data

import com.example.nobelmemory.domain.NobelPrize
import com.example.nobelmemory.domain.PrizeRepository

class InMemoryPrizeRepository(
    private val prizes: List<NobelPrize>
) : PrizeRepository {
    override suspend fun getAll(): List<NobelPrize> = prizes

    override suspend fun getByYearAndCategory(year: Int, category: String): NobelPrize? =
        prizes.firstOrNull { it.year == year && it.category.equals(category, ignoreCase = true) }
}
