package com.david.composeroyal.data.api

import com.david.composeroyal.data.models.CardItems
import com.david.composeroyal.data.models.CardResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class CardsApi(
    private val httpClient: HttpClient
) {
    suspend fun getAllCard(): CardItems {
        return try {
            val response = httpClient.get {
                url(ApiRoutes.CARDS_PATH)
            }
            response.body()
        } catch (e: Exception) {
            CardItems(emptyList())
        }
    }
}
