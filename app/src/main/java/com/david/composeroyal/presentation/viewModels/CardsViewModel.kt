package com.david.composeroyal.presentation.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.composeroyal.domain.useCase.GetAllCardsUseCase
import com.david.composeroyal.presentation.states.AllCardsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getAllCardsUseCase: GetAllCardsUseCase,
) : ViewModel() {
    private val _allCardsState: MutableState<AllCardsState> =
        mutableStateOf(AllCardsState.Empty)

    val allCardsState: MutableState<AllCardsState>
        get() = _allCardsState

    init {
        getAllCards()
    }

    private fun getAllCards() {
        _allCardsState.value = AllCardsState.Loading

        getAllCardsUseCase().catch {
            _allCardsState.value = AllCardsState.Error
        }.map { cards ->
            _allCardsState.value = AllCardsState.Success(cards)
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}
