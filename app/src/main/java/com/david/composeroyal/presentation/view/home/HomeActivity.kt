package com.david.composeroyal.presentation.view.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.david.composeroyal.presentation.states.AllCardsState
import com.david.composeroyal.presentation.theme.ComposeRoyalTheme
import com.david.composeroyal.presentation.viewModels.CardsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val cardsViewModel: CardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cardsState = cardsViewModel.allCardsState

            ComposeRoyalTheme {
                Surface {
                    when (cardsState.value) {
                        AllCardsState.Empty -> {
                            println("Empty")
                        }

                        AllCardsState.Loading -> {
                            println("Loading")
                        }

                        AllCardsState.Error -> {
                            println("Error")
                        }

                        is AllCardsState.Success -> {
                            println("Success")
                            HomeScreen(cards = (cardsState.value as AllCardsState.Success).cardsCollection)
                        }
                    }
                }
            }
        }
    }
}
