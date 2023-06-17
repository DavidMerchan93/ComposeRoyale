package com.david.composeroyal.presentation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.composeroyal.common.getString
import com.david.composeroyal.domain.useCase.GetArtistByQueryUseCase
import com.david.composeroyal.presentation.states.ArtistsState
import com.david.composeroyal.presentation.view.navigation.NavigationArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ArtistByQueryViewModel @Inject constructor(
    private val getArtistByQueryUseCase: GetArtistByQueryUseCase,
    stateHandle: SavedStateHandle,
) : ViewModel() {

    var allArtistState by mutableStateOf(ArtistsState(isLoading = true))
        private set

    init {
        stateHandle.getString(NavigationArgs.CATEGORY_NAME.key)?.let { categoryName ->
            getAllArtistsByQuery(categoryName)
        }
    }

    private fun getAllArtistsByQuery(query: String) {
        getArtistByQueryUseCase(query).catch {
            allArtistState = ArtistsState(hasError = true)
        }.map {
            allArtistState = ArtistsState(artist = it)
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}
