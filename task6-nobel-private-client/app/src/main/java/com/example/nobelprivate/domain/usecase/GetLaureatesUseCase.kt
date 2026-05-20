package com.example.nobelprivate.domain.usecase

import com.example.nobelprivate.domain.repository.PrivateNobelRepository
import javax.inject.Inject

class GetLaureatesUseCase @Inject constructor(
    private val repository: PrivateNobelRepository
) {
    suspend operator fun invoke(year: Int?, category: String?) = repository.getLaureates(year, category)
}
