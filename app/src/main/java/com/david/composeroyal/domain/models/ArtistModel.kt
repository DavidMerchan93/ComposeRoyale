package com.david.composeroyal.domain.models

data class ArtistModel(
    val id: String,
    val name: String,
    val images: List<String>,
    val externalUrl: String,
    val followersCount: Int,
    val genres: List<String>,
    val popularity: Int,
)
