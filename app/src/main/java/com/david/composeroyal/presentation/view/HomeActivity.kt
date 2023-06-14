package com.david.composeroyal.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.david.composeroyal.presentation.theme.ComposeRoyalTheme
import com.david.composeroyal.presentation.view.navigation.NavigationApp
import com.david.composeroyal.presentation.viewModels.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val categoriesViewModel: CategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val categoriesState = categoriesViewModel.allCategoriesState

            ComposeRoyalTheme {
                NavigationApp(categoriesState)
            }
        }
    }
}
