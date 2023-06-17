package com.david.composeroyal.domain.repositories

import com.david.composeroyal.domain.models.TrackModel
import kotlinx.coroutines.flow.Flow

interface TracksRepository {
    fun getTracks(id: String): Flow<List<TrackModel>>
}
