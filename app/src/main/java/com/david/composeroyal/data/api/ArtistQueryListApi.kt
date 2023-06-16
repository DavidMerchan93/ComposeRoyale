package com.david.composeroyal.data.api

import com.david.composeroyal.data.api.common.ApiRoutes
import com.david.composeroyal.data.models.search.SearchArtistResponse
import com.david.composeroyal.data.network.KtorHttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArtistQueryListApi(
    private val httpClient: KtorHttpClient,
) {

    suspend fun getArtistByQuery(
        query: String,
    ): Result<SearchArtistResponse> {
        return try {
            val response = httpClient.getHttpClient().get(ApiRoutes.SEARCH_PATH) {
                url {
                    parameters.append("q", query)
                    parameters.append("type", "artist")
                    parameters.append("limit", "30")
                }
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
