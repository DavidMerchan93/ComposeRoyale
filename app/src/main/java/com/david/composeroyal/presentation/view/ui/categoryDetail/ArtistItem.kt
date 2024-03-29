package com.david.composeroyal.presentation.view.ui.categoryDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.david.composeroyal.R
import com.david.composeroyal.domain.models.ArtistModel

@Composable
fun ArtistList(
    artists: List<ArtistModel>,
    artistSelect: (artistId: String) -> Unit,
) {
    val artistsList = remember(artists) {
        artists.sortedBy { it.followersCount }
    }

    LazyColumn(
        state = rememberLazyListState(),
        content = {
            items(
                items = artistsList,
                key = { artist ->
                    artist.id
                },
            ) { artist ->
                ArtistItem(
                    artist = artist,
                    artistSelect = artistSelect,
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ArtistItem(artist: ArtistModel, artistSelect: (artistId: String) -> Unit) {
    Card(
        modifier = Modifier.padding(dimensionResource(R.dimen.dimen_8dp)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.dimen_8dp)),
        border = BorderStroke(1.dp, colorResource(R.color.purple_200)),
        onClick = {
            artistSelect(artist.id)
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ImageArtist(
                image = artist.images.randomOrNull(),
                name = artist.name,
            )
            ArtisData(artist)
        }
    }
}

@Composable
private fun ImageArtist(
    image: String?,
    name: String,
) {
    AsyncImage(
        modifier = Modifier
            .width(dimensionResource(R.dimen.dimen_120dp))
            .fillMaxHeight(),
        model = image ?: R.drawable.ic_launcher_foreground,
        contentDescription = name,
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun ArtisData(artist: ArtistModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.dimen_16dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = artist.name,
            color = Color.DarkGray,
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_4dp)))
        Text(
            text = artist.followersCount.toString(),
            color = Color.DarkGray,
            style = MaterialTheme.typography.body1,
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_4dp)))
        ChipGenders(artist.genres)
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
private fun ChipGenders(genres: List<String>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dimen_4dp)),
    ) {
        genres.forEach { gender ->
            Chip(
                onClick = {},
                content = {
                    Text(
                        text = gender,
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                    )
                },
            )
        }
    }
}
