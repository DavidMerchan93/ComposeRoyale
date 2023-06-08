package com.david.composeroyal.domain.repositories

import com.david.composeroyal.data.models.CardResponse
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun getAllCards(): Flow<List<CardResponse>>
}
