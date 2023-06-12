package com.david.composeroyal.domain.repositories

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun getAccessToken(): String
}
