package com.david.composeroyal.domain.repositories

import com.david.composeroyal.domain.models.ArtistModel
import kotlinx.coroutines.flow.Flow

interface ArtistByQueryRepository {
    fun getArtistByQuery(query: String): Flow<List<ArtistModel>>
}
