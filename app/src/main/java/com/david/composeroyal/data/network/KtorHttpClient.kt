package com.david.composeroyal.data.network

import com.david.composeroyal.data.api.common.Constants
import com.david.composeroyal.data.local.PreferenceSettings
import com.david.composeroyal.data.models.InvalidTokenException
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first

private const val TIME_OUT = 60_000

fun ktorHttpClient(
    preferencesSettings: PreferenceSettings,
): HttpClient {
    val client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json()
        }
        install(DefaultRequest) {
            // header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
        HttpResponseValidator {
            validateResponse { response ->
                when (val statusCode = response.status.value) {
                    401 -> {
                        throw InvalidTokenException(response, "Error: $statusCode")
                    }

                    in 300..399 -> {
                        throw RedirectResponseException(response, "Error: $statusCode")
                    }

                    in 400..499 -> {
                        throw ClientRequestException(response, "Error: $statusCode")
                    }

                    else -> {
                        throw ResponseException(response, "Error: $statusCode")
                    }
                }
            }
        }
    }

    client.sendPipeline.intercept(HttpSendPipeline.State) {
        context.headers.append(Constants.AUTHORIZATION, preferencesSettings.accessToken.first())
    }

    return client
}
