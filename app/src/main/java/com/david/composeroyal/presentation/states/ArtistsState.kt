package com.david.composeroyal.presentation.states

import com.david.composeroyal.domain.models.ArtistModel

data class ArtistsState(
    val isLoading: Boolean = false,
    val artist: List<ArtistModel> = emptyList(),
    val hasError: Boolean = false
)
