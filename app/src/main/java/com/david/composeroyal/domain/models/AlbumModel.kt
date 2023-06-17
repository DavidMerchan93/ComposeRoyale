package com.david.composeroyal.domain.models

data class AlbumModel(
    val id: String,
    val name: String,
    val albumType: String,
    val images: List<String>,
    val releasedDate: String,
    val type: String,
    val uri: String,
)
