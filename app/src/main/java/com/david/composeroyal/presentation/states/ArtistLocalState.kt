package com.david.composeroyal.presentation.states

import com.david.composeroyal.domain.models.ArtistModel

data class ArtistLocalState(
    val isSaved: Boolean = false,
    val isDeleted: Boolean = false,
    val allArtist: List<ArtistModel> = emptyList(),
    val artist: ArtistModel? = null,
)
