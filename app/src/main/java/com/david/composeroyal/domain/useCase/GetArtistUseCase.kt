package com.david.composeroyal.domain.useCase

import com.david.composeroyal.domain.common.retryUpdateToken
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.repositories.ArtistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistUseCase @Inject constructor(
    private val artistRepository: ArtistRepository,
    private val updateTokenUseCase: UpdateAccessTokenUseCase,
) {
    operator fun invoke(id: String): Flow<ArtistModel> =
        artistRepository.getArtist(id).retryUpdateToken(updateTokenUseCase)
}
