package com.david.composeroyal.states

import com.david.domain.models.CardModel

sealed class AllCardsState {

    object Loading : AllCardsState()

    data class Success(val cards: List<CardModel>) : AllCardsState()

    object Error : AllCardsState()

    object Empty : AllCardsState()
}
