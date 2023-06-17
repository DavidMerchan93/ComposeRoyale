package com.david.composeroyal.domain.useCase

import com.david.composeroyal.domain.common.retryUpdateToken
import com.david.composeroyal.domain.models.TrackModel
import com.david.composeroyal.domain.repositories.TracksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTracksUseCase @Inject constructor(
    private val tracksRepository: TracksRepository,
    private val getAccessTokenUseCase: UpdateAccessTokenUseCase,
) {
    operator fun invoke(id: String): Flow<List<TrackModel>> =
        tracksRepository.getTracks(id).retryUpdateToken(getAccessTokenUseCase)
}
