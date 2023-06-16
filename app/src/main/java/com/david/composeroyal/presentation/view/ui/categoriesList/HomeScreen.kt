package com.david.composeroyal.presentation.view.ui.categoriesList

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.composeroyal.presentation.view.common.ErrorMessage
import com.david.composeroyal.presentation.view.common.Loader
import com.david.composeroyal.presentation.viewModels.CategoriesViewModel

@Composable
fun HomeScreen(
    categoriesViewModel: CategoriesViewModel = hiltViewModel(),
    onCategorySelected: (id: String, name: String) -> Unit,
) {
    val categoriesState = categoriesViewModel.allCategoriesState
    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
    ) {
        when {
            (categoriesState.loading) -> {
                Loader()
            }

            (categoriesState.cardsCollection.isNotEmpty()) -> {
                HomeScreenList(
                    cards = categoriesState.cardsCollection,
                    onCategorySelected = onCategorySelected,
                )
            }

            (categoriesState.isError) -> {
                ErrorMessage()
            }
        }
    }
}
