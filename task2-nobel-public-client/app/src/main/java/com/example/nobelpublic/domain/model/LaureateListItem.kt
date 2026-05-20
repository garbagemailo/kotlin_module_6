package com.example.nobelpublic.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LaureateListItem(
    val id: String,
    val year: Int,
    val category: String,
    val fullName: String,
    val motivationShort: String,
    val motivationFull: String
)
