package com.david.composeroyal.presentation.view.ui.categoryDetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.david.composeroyal.presentation.view.common.ErrorMessage
import com.david.composeroyal.presentation.view.common.Loader
import com.david.composeroyal.presentation.viewModels.ArtistByQueryViewModel

@Composable
fun CategoryDetailScreen(
    artistsListViewModel: ArtistByQueryViewModel = hiltViewModel(),
    artistSelected: (artistId: String) -> Unit,
) {
    val artistsListState = artistsListViewModel.allArtistState

    when {
        (artistsListState.isLoading) -> {
            Loader()
        }

        (artistsListState.artist.isNotEmpty()) -> {
            ArtistList(
                artists = artistsListState.artist,
                artistSelect = artistSelected,
            )
        }

        (artistsListState.hasError) -> {
            ErrorMessage()
        }
    }
}
