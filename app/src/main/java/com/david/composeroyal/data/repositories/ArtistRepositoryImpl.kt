package com.david.composeroyal.data.repositories

import com.david.composeroyal.data.api.ArtistApi
import com.david.composeroyal.data.models.artist.mapToDomain
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.repositories.ArtistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArtistRepositoryImpl(
    private val artistApi: ArtistApi,
) : ArtistRepository {
    override fun getArtist(id: String): Flow<ArtistModel> = flow {
        val response = artistApi.getArtistData(id)
        when {
            response.isSuccess -> {
                response.getOrNull()?.let {
                    emit(it.mapToDomain())
                } ?: throw Throwable("Error in artist null")
            }

            response.isFailure -> {
                throw response.exceptionOrNull() ?: Throwable("Error in artist")
            }
        }
    }
}
