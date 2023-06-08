package com.david.composeroyal.presentation.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.david.composeroyal.domain.models.CardModel

@Composable
fun CardItem(modifier: Modifier = Modifier, card: CardModel) {
    Column(
        modifier = modifier.width(150.dp).padding(8.dp),
    ) {
        Text(text = card.name)
    }
}
