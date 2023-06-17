package com.david.composeroyal.presentation.view.ui.categoryDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
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
fun ArtistDetailScreen(artistViewModel: ArtistViewModel = hiltViewModel()) {
    val artisTrackState = artistViewModel.artisTrackState
    val artist = artisTrackState.artist
    val tracks = artisTrackState.tracks

    when {
        artisTrackState.isLoading -> {
            Loader()
        }

        artist != null -> {
            ArtistInformation(artist)
        }

        tracks.isNotEmpty() -> {
            ArtistTracks(tracks)
        }

        artisTrackState.isError -> {
            ErrorMessage()
        }
    }
}

@Composable
fun ArtistDetailAndTracks(
    artistModel: ArtistModel?,
    tracks: List<TrackModel>,
    addToFavorites: (artist: ArtistModel) -> Unit,
    openTrack: (track: TrackModel) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        ArtistInformation(artistModel)
        ArtistTracks(tracks)
    }
}

@Composable
fun ArtistInformation(artistModel: ArtistModel?) {
    artistModel?.let {
        ArtistLargeImage(image = it.images.randomOrNull())
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dimen_16dp)),
            text = it.name,
            textAlign = TextAlign.Center,
        )
        TrackInfo(
            followers = it.followersCount,
            popularity = it.popularity,
        )
    }
}

@Composable
fun ArtistTracks(tracksList: List<TrackModel>) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.dimen_16dp)),
        text = "Top Tracks",
        textAlign = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dimen_16dp)))
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(R.dimen.dimen_120dp)),
        content = {
            items(tracksList) { track ->
                TrackItem(track)
            }
        },
    )
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
fun TrackInfo(
    followers: Int,
    popularity: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.dimen_8dp)),
    ) {
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = followers.toString(),
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = popularity.toString(),
        )
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.Favorite,
            contentDescription = "add",
        )
    }
}

@Composable
fun TrackItem(track: TrackModel) {
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.dimen_4dp)),
        border = BorderStroke(1.dp, colorResource(R.color.purple_700)),
    ) {
        track.album?.let { album ->
            Column(
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
