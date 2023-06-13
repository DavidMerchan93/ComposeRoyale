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
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

private const val TIME_OUT = 60_000

class KtorHttpClientImpl(
    private val preferencesSettings: PreferenceSettings,
) : KtorHttpClient {

    override fun getHttpClient(): HttpClient {
        val client = HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    },
                )
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
            HttpResponseValidator {
                validateResponse { response ->
                    checkResponseErrors(response, response.status.value)
                }
            }
        }

        client.sendPipeline.intercept(HttpSendPipeline.State) {
            context.headers.append(Constants.AUTHORIZATION, preferencesSettings.accessToken.first())
        }

        return client
    }

    override fun getAccountTokenHttpClient(): HttpClient {
        val client = HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json()
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded)
            }
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
            HttpResponseValidator {
                validateResponse { response ->
                    checkResponseErrors(response, response.status.value)
                }
            }
        }

        return client
    }

    private fun checkResponseErrors(response: HttpResponse, errorCode: Int) {
        when (errorCode) {
            401 -> {
                throw InvalidTokenException(response, "Error: $errorCode")
            }

            in 300..399 -> {
                throw RedirectResponseException(response, "Error: $errorCode")
            }

            in 400..499 -> {
                throw ClientRequestException(response, "Error: $errorCode")
            }
        }
    }
}
