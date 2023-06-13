package com.david.composeroyal.data.api

import com.david.composeroyal.data.api.common.ApiRoutes
import com.david.composeroyal.data.api.common.Constants
import com.david.composeroyal.data.models.TokenResponse
import com.david.composeroyal.data.network.KtorHttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.http.parameters

class TokenApi(
    private val httpClient: KtorHttpClient,
) {
    suspend fun getToken(): Result<TokenResponse> {
        return try {
            val response = httpClient.getAccountTokenHttpClient().submitForm(
                url = ApiRoutes.TOKEN_PATH,
                formParameters = parameters {
                    append("grant_type", "client_credentials")
                    append("client_id", Constants.CLIENT_ID)
                    append("client_secret", Constants.CLIENT_SECRET)
                },
            )
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
