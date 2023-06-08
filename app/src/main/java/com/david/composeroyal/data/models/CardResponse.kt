package com.david.composeroyal.data.models

import com.david.composeroyal.domain.models.CardModel
import kotlinx.serialization.Serializable

@Serializable
data class CardItems(
    val items: List<CardResponse>,
)

@Serializable
data class CardResponse(
    val id: Long,
    val name: String,
    val maxLevel: Int,
    val iconUrls: Icon,
) {
    @Serializable
    data class Icon(
        val medium: String,
    )
}

fun CardResponse.mapToDomain(): CardModel = CardModel(
    id = id,
    name = name,
    maxLevel = maxLevel,
    icon = iconUrls.medium,
)
