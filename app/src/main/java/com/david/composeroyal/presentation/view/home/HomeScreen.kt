package com.david.composeroyal.presentation.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.david.composeroyal.domain.models.CategoriesModel
import com.david.composeroyal.presentation.states.CategoriesState
import com.david.composeroyal.presentation.view.common.ErrorMessage
import com.david.composeroyal.presentation.view.common.Loader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    categoriesState: MutableState<CategoriesState>,
    onCategorySelected: (id: String, name: String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Music")
                },
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier.padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),
            ) {
                when (categoriesState.value) {
                    CategoriesState.Empty -> Unit

                    CategoriesState.Loading -> {
                        Loader()
                    }

                    CategoriesState.Error -> {
                        ErrorMessage()
                    }

                    is CategoriesState.Success -> {
                        HomeScreenList(
                            cards = (categoriesState.value as CategoriesState.Success).cardsCollection,
                            onCategorySelected = onCategorySelected,
                        )
                    }
                }
            }
        },
    )
}

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
