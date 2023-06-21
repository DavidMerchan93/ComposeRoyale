package com.david.composeroyal.presentation.view.ui.categoryDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.david.composeroyal.R
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.models.TrackModel
import com.david.composeroyal.presentation.view.common.ErrorMessage
import com.david.composeroyal.presentation.view.common.Loader
import com.david.composeroyal.presentation.viewModels.ArtistViewModel

@Composable
fun ArtistDetailScreen(
    artistViewModel: ArtistViewModel = hiltViewModel(),
) {
    val artisTrackState = artistViewModel.artisTrackState

    when {
        artistViewModel.artistInfo != null && artistViewModel.tracksArtistInfo.isNotEmpty() -> {
            AT(
                artistModel = artistViewModel.artistInfo,
                tracks = artistViewModel.tracksArtistInfo,
                addToFavorites = { },
            )
        }

        artisTrackState.isError -> {
            ErrorMessage()
        }

        else -> {
            Loader()
        }
    }
}

@Composable
fun AT(
    artistModel: ArtistModel?,
    tracks: List<TrackModel>,
    addToFavorites: (artist: ArtistModel) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            dimensionResource(R.dimen.dimen_8dp),
            dimensionResource(R.dimen.dimen_4dp),
        ),
        content = {
            item {
                ArtistInformation(artistModel, addToFavorites)
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.dimen_16dp)),
                    text = stringResource(R.string.artist_detail_top_tracks_title),
                    textAlign = TextAlign.Center,
                )
            }
            items(tracks) { track ->
                TrackItem(track)
            }
        },
    )
}

@Composable
fun ArtistInformation(artistModel: ArtistModel?, addToFavorites: (artist: ArtistModel) -> Unit) {
    artistModel?.let {
        ArtistLargeImage(image = it.images.randomOrNull())
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dimen_16dp)),
            text = it.name,
            textAlign = TextAlign.Center,
        )
        ArtistData(
            followers = it.followersCount,
            popularity = it.popularity,
            addToFavorites = { addToFavorites(artistModel) },
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackItem(track: TrackModel) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.dimen_8dp)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.dimen_4dp)),
        border = BorderStroke(1.dp, colorResource(R.color.purple_700)),
        onClick = {
            openTrack(context, track.uri)
        },
    ) {
        track.album?.let { album ->
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.dimen_8dp))
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.dimen_80dp))
                        .height(dimensionResource(R.dimen.dimen_80dp))
                        .clip(CircleShape),
                    model = album.images.randomOrNull() ?: R.drawable.ic_launcher_foreground,
                    contentDescription = album.name,
                )
                TextTrack(name = track.name)
                TextTrack(name = track.popularity.toString())
                TextTrack(name = album.name)
            }
        }
    }
}

@Composable
fun ArtistLargeImage(image: String?) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.dimen_120dp)),
        model = image ?: R.drawable.ic_launcher_foreground,
        contentDescription = image ?: String(),
    )
}

@Composable
fun ArtistData(
    followers: Int,
    popularity: Int,
    addToFavorites: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.dimen_8dp)),
    ) {
        ArtistInformationItem(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.artist_detail_followers),
            value = followers.toString(),
        )

        ArtistInformationItem(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.artist_detail_popularity),
            value = popularity.toString(),
        )

        IconButton(
            modifier = Modifier.weight(1f),
            content = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    tint = Color.Red,
                    contentDescription = "add",
                )
            },
            onClick = {
                addToFavorites()
            },
        )
    }
}

@Composable
fun ArtistInformationItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = title,
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_4dp)))
        Text(
            textAlign = TextAlign.Center,
            text = value,
        )
    }
}

@Composable
fun TextTrack(name: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.dimen_8dp)),
        text = name,
        textAlign = TextAlign.Center,
        color = colorResource(R.color.purple_200),
        style = MaterialTheme.typography.body1,
    )
}

private fun openTrack(context: Context, url: String) {
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}
