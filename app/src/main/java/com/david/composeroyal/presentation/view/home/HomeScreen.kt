package com.david.composeroyal.presentation.view.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.david.composeroyal.domain.models.CardModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    cards: List<CardModel>,
) {
    LazyColumn {
        items(cards) {
            CardItem(card = it)
        }
    }
}
