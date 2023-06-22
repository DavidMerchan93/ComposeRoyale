package com.david.composeroyal.presentation.view.ui.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.presentation.view.common.ErrorMessage
import com.david.composeroyal.presentation.view.common.Loader
import com.david.composeroyal.presentation.viewModels.ArtistLocalViewModel

@Composable
fun FavoriteScreen(
    artistLocalViewModel: ArtistLocalViewModel = hiltViewModel(),
) {
    val artistLocalState = artistLocalViewModel.artistLocalState

    when {
        artistLocalState.allArtist.isNotEmpty() -> {
            ArtistLocalList(artistList = artistLocalState.allArtist)
        }

        artistLocalState.allArtist.isEmpty() -> {
            artistLocalViewModel.getAllArtist()
        }

        artistLocalState.isError -> {
            ErrorMessage()
        }

        artistLocalState.isLoader -> {
            Loader()
        }
    }
}

@Composable
fun ArtistLocalList(artistList: List<ArtistModel>) {
    LazyColumn(content = {
        items(artistList) { artist ->
            Text(text = artist.name)
        }
    })
}
