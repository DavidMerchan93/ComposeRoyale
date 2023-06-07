package com.david.data.api

import com.david.data.models.CardResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import java.lang.Exception

class CardsApi(
    private val httpClient: HttpClient,
) {
    suspend fun getAllCard(): List<CardResponse> {
        return try {
            val response = httpClient.get {
                url("")
                header("", "")
            }
            response.body()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
