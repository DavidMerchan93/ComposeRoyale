package com.david.composeroyal.presentation.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.david.composeroyal.domain.models.CardModel

@Composable
fun CardItem(card: CardModel) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp),
        ) {
            AsyncImage(
                model = card.icon,
                contentDescription = card.name,
                modifier = Modifier.fillMaxWidth().height(100.dp),
            )
            CardText(text = "Id: ${card.id}", fontSize = 14)
            CardText(text = card.name, fontSize = 18)
            CardText(text = "Max Level: ${card.maxLevel}", fontSize = 14)
        }
    }
}

@Composable
fun CardText(text: String, fontSize: Int) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        text = text,
        fontSize = fontSize.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}

@Preview
@Composable
fun CardPreview() {
    Surface {
        CardItem(
            card = CardModel(
                id = 12L,
                name = "David",
                maxLevel = 100,
                icon = "",
            ),
        )
    }
}
