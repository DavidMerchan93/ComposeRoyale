package com.david.composeroyal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.david.composeroyal.ui.theme.ComposeRoyalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRoyalTheme {
                Surface {
                    ListItem(
                        listOf(
                            MyItem("title 1", "message 1"),
                            MyItem("title 2", "message 2"),
                            MyItem("title 3", "message 3"),
                            MyItem("title 4", "message 4"),
                            MyItem("title 5", "message 5"),
                            MyItem("title 6", "message 6"),
                            MyItem("title 7", "message 7"),
                            MyItem("title 8", "message 8"),
                            MyItem("title 9", "message 9"),
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun Item(title: String, message: String, modifier: Modifier = Modifier) {
    Int.MAX_VALUE
    Column(modifier = modifier.padding(16.dp)) {
        Row(modifier = modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Imagen de prueba",
                modifier = modifier.width(64.dp),
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Hello $title!",
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = message,
                    modifier = modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
        LinearProgressIndicator(
            modifier = modifier
                .padding(0.dp, 8.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
fun ListItem(data: List<MyItem>) {
    LazyColumn {
        items(data) { item ->
            Item(item.title, item.message)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeRoyalTheme {
        Surface {
            ListItem(
                listOf(
                    MyItem("title 1", "message 1"),
                    MyItem("title 2", "message 2"),
                    MyItem("title 3", "message 3"),
                    MyItem("title 4", "message 4"),
                    MyItem("title 5", "message 5"),
                    MyItem("title 6", "message 6"),
                    MyItem("title 7", "message 7"),
                    MyItem("title 8", "message 8"),
                    MyItem("title 9", "message 9"),
                ),
            )
        }
    }
}

data class MyItem(
    val title: String,
    val message: String,
)
