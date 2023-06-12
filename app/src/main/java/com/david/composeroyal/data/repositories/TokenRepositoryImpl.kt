package com.david.composeroyal.data.repositories

import com.david.composeroyal.data.api.TokenApi
import com.david.composeroyal.domain.repositories.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenApi: TokenApi,
) : TokenRepository {

    override suspend fun getAccessToken(): String {
        val result = tokenApi.getToken()

        when {
            result.isSuccess -> {
                return result.getOrNull()?.accessToken ?: String()
            }

            else -> {
                throw result.exceptionOrNull() ?: Throwable("Error al obtener el token")
            }
        }
    }
}
