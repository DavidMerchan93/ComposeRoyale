package com.david.composeroyal.data.api

import com.david.composeroyal.data.api.common.ApiRoutes
import com.david.composeroyal.data.models.artist.ArtistResponse
import com.david.composeroyal.data.network.KtorHttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class ArtistApi(
    private val httpClient: KtorHttpClient,
) {
    suspend fun getArtistData(id: String): Result<ArtistResponse> {
        return try {
            val response = httpClient.getHttpClient().get(ApiRoutes.ARTIST_PATH) {
                url {
                    appendPathSegments(id)
                }
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
