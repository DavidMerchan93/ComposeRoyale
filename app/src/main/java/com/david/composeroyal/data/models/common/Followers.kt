package com.david.composeroyal.data.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Followers(
    val href: String? = String(),
    val total: Int,
)
