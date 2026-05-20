package com.example.nobelprivate.domain.repository

import com.example.nobelprivate.domain.model.LaureateDetail
import com.example.nobelprivate.domain.model.LaureateListItem

interface PrivateNobelRepository {
    suspend fun getLaureates(year: Int?, category: String?): List<LaureateListItem>
    suspend fun getLaureateDetail(item: LaureateListItem): LaureateDetail
}
