package com.david.composeroyal.data.api

import com.david.composeroyal.data.api.common.ApiRoutes
import com.david.composeroyal.data.models.CardItems
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class CardsApi(
    private val httpClient: HttpClient,
) {
    suspend fun getAllCard(): Result<CardItems> {
        return try {
            val response = httpClient.get {
                url(ApiRoutes.CARDS_PATH)
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
