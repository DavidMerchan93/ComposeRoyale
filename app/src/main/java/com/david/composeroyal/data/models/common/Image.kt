package com.david.composeroyal.data.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val url: String,
    val height: Int? = 0,
    val width: Int? = 0,
)
