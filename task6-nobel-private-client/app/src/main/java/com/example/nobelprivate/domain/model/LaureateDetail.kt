package com.example.nobelprivate.domain.model

data class LaureateDetail(
    val id: Int,
    val fullName: String,
    val year: Int,
    val category: String,
    val motivation: String,
    val birthCountry: String?,
    val birthPlace: String?,
    val portraitUrl: String?
)
