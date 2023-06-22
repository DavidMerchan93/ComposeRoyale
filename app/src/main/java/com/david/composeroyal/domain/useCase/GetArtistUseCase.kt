package com.david.composeroyal.domain.useCase

import com.david.composeroyal.domain.common.retryUpdateToken
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.repositories.ArtistRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip

class GetArtistUseCase @Inject constructor(
    private val artistRepository: ArtistRepository,
    private val checkLocalArtistUseCase: CheckLocalArtistUseCase,
    private val updateTokenUseCase: UpdateAccessTokenUseCase,
) {
    operator fun invoke(id: String): Flow<ArtistModel> =
        artistRepository.getArtist(id).zip(checkLocalArtistUseCase(id)) { a1, a2 ->
            a1.copy(isFavorite = a2 != null)
        }.retryUpdateToken(updateTokenUseCase)
}
