package com.david.composeroyal.domain.useCase

import com.david.composeroyal.domain.common.retryUpdateToken
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.repositories.ArtistByQueryRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetArtistByQueryUseCase @Inject constructor(
    private val artistByQueryRepository: ArtistByQueryRepository,
    private val tokenUseCase: UpdateAccessTokenUseCase,
) {
    operator fun invoke(query: String): Flow<List<ArtistModel>> =
        artistByQueryRepository.getArtistByQuery(query).retryUpdateToken(tokenUseCase)
}
