package com.example.nobelprivate.domain.usecase

import com.example.nobelprivate.domain.model.LaureateListItem
import com.example.nobelprivate.domain.repository.PrivateNobelRepository
import javax.inject.Inject

class GetLaureateDetailUseCase @Inject constructor(
    private val repository: PrivateNobelRepository
) {
    suspend operator fun invoke(item: LaureateListItem) = repository.getLaureateDetail(item)
}
