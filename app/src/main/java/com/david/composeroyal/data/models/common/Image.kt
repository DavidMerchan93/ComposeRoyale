package com.david.composeroyal.data.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val url: String,
    val height: Int? = 0,
    val width: Int? = 0,
)

fun List<Image>.mapToDomain(): List<String> {
    return map { it.url }
}
