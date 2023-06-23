package com.david.composeroyal.presentation.view.ui.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.david.composeroyal.R
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.presentation.view.common.ErrorMessage
import com.david.composeroyal.presentation.view.common.Loader
import com.david.composeroyal.presentation.viewModels.ArtistLocalViewModel

@Composable
fun FavoriteScreen(
    artistLocalViewModel: ArtistLocalViewModel = hiltViewModel(),
) {
    artistLocalViewModel.getAllArtist()
    ArtistLocalList(artistLocalViewModel)
}

@Composable
fun ArtistLocalList(artistLocalViewModel: ArtistLocalViewModel) {
    val artistLocalState = artistLocalViewModel.artistLocalState
    when {
        artistLocalState.isError -> {
            ErrorMessage()
        }

        artistLocalState.isLoader -> {
            Loader()
        }

        artistLocalState.allArtist.isNotEmpty() -> {
            FavoriteArtistLis(artistLocalState.allArtist)
        }

        else -> {
            EmptyFavoritesScreen()
        }
    }
}

@Composable
fun EmptyFavoritesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.favorites_empty_text),
            color = colorResource(R.color.purple_200),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
fun FavoriteArtistLis(allArtist: List<ArtistModel>) {
    LazyColumn(content = {
        items(allArtist) { artist ->
            ItemFavorite(artist)
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemFavorite(artistModel: ArtistModel) {
    Card(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.dimen_8dp))
            .fillMaxWidth(),
        onClick = {
            throw RuntimeException("Test Crash")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.dimen_8dp)),
        ) {
            AsyncImage(
                model = artistModel.images.randomOrNull() ?: R.drawable.artist_null,
                contentDescription = artistModel.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.dimen_180dp)),
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_8dp)))
            ArtistItemText(
                modifier = Modifier.fillMaxWidth(),
                text = artistModel.name,
                textSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_8dp)))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.dimen_8dp)),
            ) {
                ArtistItemText(
                    modifier = Modifier.weight(1f),
                    text = stringResource(
                        R.string.favorites_popularity_title,
                        artistModel.popularity.toString(),
                    ),
                )
                ArtistItemText(
                    modifier = Modifier.weight(1f),
                    text = stringResource(
                        R.string.favorites_followers_title,
                        artistModel.followersCount.toString(),
                    ),
                )
            }
        }
    }
}

@Composable
fun ArtistItemText(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = 18.sp,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        modifier = modifier,
        text = text,
        color = colorResource(R.color.secondary),
        fontSize = textSize,
        textAlign = TextAlign.Center,
        fontWeight = fontWeight,
    )
}
