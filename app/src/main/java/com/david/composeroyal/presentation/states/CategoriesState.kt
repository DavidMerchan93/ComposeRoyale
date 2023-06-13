package com.david.composeroyal.presentation.states

import com.david.composeroyal.domain.models.CategoriesModel

sealed class CategoriesState {

    object Loading : CategoriesState()

    data class Success(val cardsCollection: List<CategoriesModel>) : CategoriesState()

    object Error : CategoriesState()

    object Empty : CategoriesState()
}
