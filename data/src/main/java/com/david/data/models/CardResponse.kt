package com.david.data.models

import com.david.domain.models.CardModel

data class CardResponse(
    val id: Long,
    val name: String,
    val maxLevel: Int,
    val icons: Icon,
) {
    data class Icon(
        val medium: String,
    )
}

fun CardResponse.mapToDomain(): CardModel = CardModel(
    id = id,
    name = name,
    maxLevel = maxLevel,
    icon = icons.medium,
)
