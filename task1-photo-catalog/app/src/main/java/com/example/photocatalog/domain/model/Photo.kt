package com.example.photocatalog.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    val thumbnailUrl: String
)
