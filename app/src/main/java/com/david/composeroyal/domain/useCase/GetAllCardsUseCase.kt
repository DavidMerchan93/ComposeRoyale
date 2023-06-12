package com.david.composeroyal.domain.useCase

import com.david.composeroyal.data.api.common.Constants
import com.david.composeroyal.data.models.InvalidTokenException
import com.david.composeroyal.data.models.mapToDomain
import com.david.composeroyal.domain.models.CardModel
import com.david.composeroyal.domain.repositories.CardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import javax.inject.Inject

class GetAllCardsUseCase @Inject constructor(
    private val cardRepository: CardRepository,
    private val tokenUseCase: UpdateAccessTokenUseCase,
) {
    operator fun invoke(): Flow<List<CardModel>> =
        cardRepository.getAllCards().map { cardResponses ->
            cardResponses.map {
                it.mapToDomain()
            }
        }.catch { exception ->
            if (exception is InvalidTokenException) {
                tokenUseCase().collect()
            }
        }.retryWhen { exception, count ->
            if (count > Constants.MAX_RETRIES) {
                false
            } else if (exception is InvalidTokenException) {
                delay(Constants.DELAY_TIME)
                true
            } else {
                throw exception
            }
        }
}
