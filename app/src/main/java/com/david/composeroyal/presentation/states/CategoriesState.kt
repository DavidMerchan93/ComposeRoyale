package com.david.composeroyal.presentation.states

import com.david.composeroyal.domain.models.CategoriesModel

data class CategoriesState(
    val loading: Boolean = false,
    val cardsCollection: List<CategoriesModel> = emptyList(),
    val error: Boolean = false,
)
