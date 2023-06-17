package com.david.composeroyal.data.api

import com.david.composeroyal.data.api.common.ApiRoutes
import com.david.composeroyal.data.models.tracks.TracksResponse
import com.david.composeroyal.data.network.KtorHttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class TracksApi(
    private val httpClient: KtorHttpClient,
) {
    suspend fun getTracksByArtist(id: String): Result<TracksResponse> {
        return try {
            val response = httpClient.getHttpClient().get(ApiRoutes.ARTIST_PATH) {
                url {
                    appendPathSegments(id, "top-tracks")
                    parameters.append("market", "ES")
                }
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
