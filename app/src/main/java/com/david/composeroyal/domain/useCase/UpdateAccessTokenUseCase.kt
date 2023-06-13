package com.david.composeroyal.domain.useCase

import com.david.composeroyal.data.local.PreferenceSettings
import com.david.composeroyal.domain.models.errors.InvalidTokenException
import com.david.composeroyal.domain.repositories.TokenRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateAccessTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val preferenceSettings: PreferenceSettings,
) {
    suspend operator fun invoke() = flow {
        val accessToken = tokenRepository.getAccessToken()
        saveAccessToken(accessToken)
        emit(accessToken)
    }.catch { error ->
        throw InvalidTokenException(error.localizedMessage ?: "Error con el token: Domain")
    }

    private suspend fun saveAccessToken(token: String) {
        preferenceSettings.saveAccessToken(token)
    }
}
