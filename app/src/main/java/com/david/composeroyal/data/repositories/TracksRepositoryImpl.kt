package com.david.composeroyal.data.repositories

import com.david.composeroyal.data.api.TracksApi
import com.david.composeroyal.data.models.tracks.mapToDomain
import com.david.composeroyal.domain.models.TrackModel
import com.david.composeroyal.domain.repositories.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TracksRepositoryImpl(
    private val tracksApi: TracksApi,
) : TracksRepository {
    override fun getTracks(id: String): Flow<List<TrackModel>> = flow {
        val response = tracksApi.getTracksByArtist(id)
        when {
            response.isSuccess -> {
                val tracks = response.getOrNull()?.tracks?.map {
                    it.mapToDomain()
                } ?: emptyList()
                emit(tracks)
            }

            response.isFailure -> {
                throw response.exceptionOrNull() ?: Throwable("Error in tracks")
            }
        }
    }
}
