package com.david.composeroyal.presentation.states

import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.models.TrackModel

data class ArtistTracksState(
    val isLoading: Boolean = false,
    val artist: ArtistModel? = null,
    val tracks: List<TrackModel> = emptyList(),
    val isError: Boolean = false,
)
