package com.david.composeroyal.presentation.view.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.david.composeroyal.domain.models.CardModel

@Composable
fun HomeScreen(cards: List<CardModel>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 8.dp,
            end = 12.dp,
            bottom = 8.dp,
        ),
        content = {
            items(cards.size) { index ->
                CardItem(card = cards[index])
            }
        },
    )
}
