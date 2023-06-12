package com.david.composeroyal.data.repositories

import com.david.composeroyal.data.api.CardsApi
import com.david.composeroyal.data.models.CardResponse
import com.david.composeroyal.domain.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardsApi: CardsApi,
) : CardRepository {
    override fun getAllCards(): Flow<List<CardResponse>> = flow {
        val result = cardsApi.getAllCard()
        when {
            result.isSuccess -> {
                emit(result.getOrNull()?.items ?: emptyList())
            }

            else -> {
                throw result.exceptionOrNull() ?: Throwable("Error al obtener la lista")
            }
        }
    }
}
