package com.example.nobelprivate.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LaureateListItem(
    val id: Int,
    val year: Int,
    val category: String,
    val fullName: String,
    val motivationShort: String,
    val motivationFull: String,
    val portraitUrl: String? = null
)
