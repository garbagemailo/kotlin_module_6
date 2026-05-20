package com.example.nobelmemory.domain

class GetPrizesUseCase(private val repository: PrizeRepository) {
    suspend operator fun invoke(): List<NobelPrize> = repository.getAll()
}

class GetPrizeByKeyUseCase(private val repository: PrizeRepository) {
    suspend operator fun invoke(year: Int, category: String): NobelPrize? = repository.getByYearAndCategory(year, category)
}

class GetLaureatesUseCase(private val repository: PrizeRepository) {
    suspend operator fun invoke(year: Int, category: String): List<Laureate>? = repository.getByYearAndCategory(year, category)?.laureates
}
