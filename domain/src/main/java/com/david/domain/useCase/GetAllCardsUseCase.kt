package com.david.domain.useCase

import com.david.data.models.mapToDomain
import com.david.domain.models.CardModel
import com.david.domain.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllCardsUseCase(
    private val cardRepository: CardRepository,
) {
    operator fun invoke(): Flow<List<CardModel>> =
        cardRepository.getAllCards().map { cardResponses ->
            cardResponses.map {
                it.mapToDomain()
            }
        }
}
