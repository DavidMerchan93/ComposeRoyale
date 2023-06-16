package com.david.composeroyal.data.repositories

import com.david.composeroyal.data.api.ArtistQueryListApi
import com.david.composeroyal.data.models.search.mapToDomain
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.repositories.ArtistByQueryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArtistByQueryRepositoryImpl @Inject constructor(
    private val artistQueryListApi: ArtistQueryListApi,
) : ArtistByQueryRepository {
    override fun getArtistByQuery(query: String): Flow<List<ArtistModel>> = flow {
        val response = artistQueryListApi.getArtistByQuery(query)
        when {
            response.isSuccess -> {
                val artistList = response.getOrNull()?.mapToDomain() ?: emptyList()
                emit(artistList)
            }

            response.isFailure -> {
                throw response.exceptionOrNull() ?: Throwable("Error al obtener la lista")
            }
        }
    }
}
