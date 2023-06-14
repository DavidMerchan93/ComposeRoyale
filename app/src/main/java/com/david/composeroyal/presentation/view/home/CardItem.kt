package com.david.composeroyal.presentation.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.david.composeroyal.domain.models.CategoriesModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(category: CategoriesModel, onCategorySelected: (id: String, name: String) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onCategorySelected(category.id, category.name)
        },
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp),
        ) {
            AsyncImage(
                model = category.icon,
                contentDescription = category.name,
                modifier = Modifier.fillMaxWidth().height(100.dp),
            )
            CardText(text = category.name, fontSize = 18)
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
