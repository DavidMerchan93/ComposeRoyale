package com.david.composeroyal.data.models

import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse

data class InvalidTokenException(
    val httpResponse: HttpResponse,
    val cachedResponseText: String,
) : ResponseException(httpResponse, cachedResponseText)
