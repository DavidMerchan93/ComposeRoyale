package com.david.composeroyal.data.models

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val accessToken: String,
    val expiresIn: Int,
    val tokenType: String,
)
