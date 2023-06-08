package com.david.composeroyal.data.network

import com.david.composeroyal.data.api.Constants
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json

private const val TIME_OUT = 60_000

fun ktorHttpClient(): HttpClient {
    val client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json()
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }

    client.sendPipeline.intercept(HttpSendPipeline.State) {
        context.headers.append(Constants.AUTHORIZATION, Constants.ACCESS_TOKEN)
    }

    return client
}
