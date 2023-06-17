package com.david.composeroyal.domain.models

data class TrackModel(
    val id: String,
    val name: String,
    val popularity: Int,
    val uri: String,
    val album: AlbumModel?,
    val artist: ArtistModel?,
)
