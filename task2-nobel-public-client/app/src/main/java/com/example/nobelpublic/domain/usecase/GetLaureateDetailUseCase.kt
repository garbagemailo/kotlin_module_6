package com.example.nobelpublic.domain.usecase

import com.example.nobelpublic.domain.model.LaureateListItem
import com.example.nobelpublic.domain.repository.NobelRepository
import javax.inject.Inject

class GetLaureateDetailUseCase @Inject constructor(
    private val repository: NobelRepository
) {
    suspend operator fun invoke(item: LaureateListItem) = repository.getLaureateDetail(item)
}
