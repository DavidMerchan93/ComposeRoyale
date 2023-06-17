package com.david.composeroyal.domain.repositories

import com.david.composeroyal.domain.models.ArtistModel
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {
    fun getArtist(id: String): Flow<ArtistModel>
}
