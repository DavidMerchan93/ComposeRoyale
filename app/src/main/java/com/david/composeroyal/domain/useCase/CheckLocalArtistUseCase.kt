package com.david.composeroyal.domain.useCase

import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.repositories.ArtistLocalRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckLocalArtistUseCase @Inject constructor(
    private val artistLocalRepository: ArtistLocalRepository,
) {
    operator fun invoke(id: String): Flow<ArtistModel?> = flow {
        emit(artistLocalRepository.getArtistById(id))
    }
}
