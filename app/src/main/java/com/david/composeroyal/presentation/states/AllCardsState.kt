package com.david.composeroyal.presentation.states

import com.david.composeroyal.domain.models.CardModel

sealed class AllCardsState {

    object Loading : AllCardsState()

    data class Success(val cardsCollection: List<CardModel>) : AllCardsState()

    object Error : AllCardsState()

    object Empty : AllCardsState()
}
