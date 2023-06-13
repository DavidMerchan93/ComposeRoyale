package com.david.composeroyal.domain.repositories

interface TokenRepository {
    suspend fun getAccessToken(): String
}
