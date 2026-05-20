package com.example.nobelpublic.domain.usecase

import com.example.nobelpublic.domain.repository.NobelRepository
import javax.inject.Inject

class GetLaureatesUseCase @Inject constructor(
    private val repository: NobelRepository
) {
    suspend operator fun invoke(year: Int?, category: String?) = repository.getLaureates(year, category)
}
