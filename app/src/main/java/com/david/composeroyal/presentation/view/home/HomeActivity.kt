package com.david.composeroyal.presentation.view.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.david.composeroyal.presentation.states.AllCardsState
import com.david.composeroyal.presentation.theme.ComposeRoyalTheme
import com.david.composeroyal.presentation.view.common.ErrorMessage
import com.david.composeroyal.presentation.view.common.Loader
import com.david.composeroyal.presentation.viewModels.CardsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val cardsViewModel: CardsViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cardsState = cardsViewModel.allCardsState

            ComposeRoyalTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Deck")
                            },
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier.padding(paddingValues)
                                .background(MaterialTheme.colorScheme.background),
                        ) {
                            when (cardsState.value) {
                                AllCardsState.Empty -> Unit

                                AllCardsState.Loading -> {
                                    Loader()
                                }

                                AllCardsState.Error -> {
                                    ErrorMessage()
                                }

                                is AllCardsState.Success -> {
                                    println("Success")
                                    HomeScreen(cards = (cardsState.value as AllCardsState.Success).cardsCollection)
                                }
                            }
                        }
                    },
                )
            }
        }
    }
}
