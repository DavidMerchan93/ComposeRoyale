package com.david.composeroyal.presentation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.composeroyal.domain.models.ArtistModel
import com.david.composeroyal.domain.repositories.ArtistLocalRepository
import com.david.composeroyal.presentation.states.ArtistLocalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistLocalViewModel @Inject constructor(
    private val artistLocalRepository: ArtistLocalRepository,
) : ViewModel() {

    var artistLocalState by mutableStateOf(ArtistLocalState())
        private set

    fun saveArtist(artistModel: ArtistModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = artistLocalRepository.saveArtist(artistModel)
            artistLocalState = ArtistLocalState(isSaved = response)
        }
    }

    fun getAllArtist() {
        artistLocalState = ArtistLocalState(isLoader = true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = artistLocalRepository.getAllArtist()
            artistLocalState = ArtistLocalState(allArtist = response)
        }
    }

    fun deleteArtist(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = artistLocalRepository.deleteArtist(id)
            artistLocalState = ArtistLocalState(isDeleted = response)
        }
    }
}
