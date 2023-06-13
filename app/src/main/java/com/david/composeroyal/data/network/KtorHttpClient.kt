package com.david.composeroyal.data.network

import io.ktor.client.HttpClient

interface KtorHttpClient {
    fun getHttpClient(): HttpClient
    fun getAccountTokenHttpClient(): HttpClient
}
