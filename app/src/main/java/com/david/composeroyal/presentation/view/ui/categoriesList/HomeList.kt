package com.david.composeroyal.presentation.view.ui.categoriesList

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.david.composeroyal.domain.models.CategoriesModel

@Composable
fun HomeScreenList(
    cards: List<CategoriesModel>,
    onCategorySelected: (id: String, name: String) -> Unit,
) {
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
                CardItem(category = cards[index], onCategorySelected = onCategorySelected)
            }
        },
    )
}
