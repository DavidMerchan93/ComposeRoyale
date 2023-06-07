package com.david.data.repositories

import com.david.data.api.CardsApi
import com.david.data.models.CardResponse
import com.david.domain.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepositoryImpl(
    private val cardsApi: CardsApi,
) : CardRepository {
    override fun getAllCards(): Flow<List<CardResponse>> = flow {
        cardsApi.getAllCard()
    }
}
