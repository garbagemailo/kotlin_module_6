package com.example.nobelpublic.domain.repository

import com.example.nobelpublic.domain.model.LaureateDetail
import com.example.nobelpublic.domain.model.LaureateListItem

interface NobelRepository {
    suspend fun getLaureates(year: Int?, category: String?): List<LaureateListItem>
    suspend fun getLaureateDetail(item: LaureateListItem): LaureateDetail
}
