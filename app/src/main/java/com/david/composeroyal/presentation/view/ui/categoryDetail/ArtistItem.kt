package com.david.composeroyal.presentation.view.ui.categoryDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.david.composeroyal.R
import com.david.composeroyal.domain.models.ArtistModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtistList(artists: List<ArtistModel>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(130.dp),
        content = {
            items(artists) { artist ->
                ArtistItem(artist) {
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun ArtistItem(artist: ArtistModel, artistSelect: (id: String) -> Unit) {
    Card(
        modifier = Modifier.padding(dimensionResource(R.dimen.dimen_8dp)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.dimen_8dp)),
        border = BorderStroke(1.dp, colorResource(R.color.purple_200)),
        onClick = {
            artistSelect(artist.id)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.black)),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.dimen_120dp))
                    .height(dimensionResource(R.dimen.dimen_120dp))
                    .clip(CircleShape),
                model = artist.images.firstOrNull() ?: R.drawable.ic_launcher_foreground,
                contentDescription = artist.name,
                contentScale = ContentScale.FillBounds,
            )
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.dimen_16dp))
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = artist.name,
                    color = Color.LightGray,
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_4dp)))
                Text(
                    text = artist.followersCount.toString(),
                    color = Color.LightGray,
                    style = MaterialTheme.typography.body1,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_4dp)))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dimen_4dp)),
                ) {
                    artist.genres.forEach { gender ->
                        Chip(
                            onClick = {},
                            content = {
                                Text(
                                    text = gender,
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun artistPrev() {
    Surface {
        ArtistItem(
            artist = ArtistModel(
                id = "String",
                name = "String",
                images = listOf(),
                externalUrl = "String",
                followersCount = 10,
                genres = listOf("Rock", "Metal", "Salsa"),
                popularity = 5,
            ),
        ) {
        }
    }
}
