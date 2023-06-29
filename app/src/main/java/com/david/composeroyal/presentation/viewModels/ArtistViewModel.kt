package com.david.composeroyal.presentation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.composeroyal.common.getString
import com.david.composeroyal.domain.useCase.GetArtistUseCase
import com.david.composeroyal.domain.useCase.GetTracksUseCase
import com.david.composeroyal.presentation.states.ArtistTracksState
import com.david.composeroyal.presentation.view.navigation.NavigationArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

@HiltViewModel
class ArtistViewModel @Inject constructor(
    private val getArtistUseCase: GetArtistUseCase,
    private val getTracksUseCase: GetTracksUseCase,
    private val stateHandle: SavedStateHandle,
) : ViewModel() {

    var artisTrackState by mutableStateOf(ArtistTracksState(isLoading = true))
        private set

    init {
        getArtistDetail()
    }

    fun getArtistDetail() {
        stateHandle.getString(NavigationArgs.ARTIST_ID.key)?.let { id ->
            getArtistData(id)
            getTopTracks(id)
        }
    }

    private fun getArtistData(id: String) {
        getArtistUseCase(id).catch {
            artisTrackState = ArtistTracksState(isError = true)
        }.map {
            artisTrackState = artisTrackState.copy(artist = it, isError = false)
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    private fun getTopTracks(id: String) {
        getTracksUseCase(id).catch {
            artisTrackState = ArtistTracksState(isError = true)
        }.map {
            artisTrackState = artisTrackState.copy(tracks = it, isError = false)
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}
