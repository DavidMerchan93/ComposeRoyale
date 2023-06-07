package com.david.domain.repositories

import com.david.data.models.CardResponse
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun getAllCards(): Flow<List<CardResponse>>
}
