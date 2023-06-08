package com.david.composeroyal.domain.useCase

import com.david.composeroyal.data.models.mapToDomain
import com.david.composeroyal.domain.models.CardModel
import com.david.composeroyal.domain.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(
    private val cardRepository: CardRepository,
) {
    operator fun invoke(): Flow<List<CardModel>> =
        cardRepository.getAllCards().map { cardResponses ->
            cardResponses.map {
                it.mapToDomain()
            }
        }
}
