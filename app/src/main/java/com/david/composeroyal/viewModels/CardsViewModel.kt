package com.david.composeroyal.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.composeroyal.states.AllCardsState
import com.david.domain.useCase.GetAllCardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CardsViewModel(
    private val getAllCardsUseCase: GetAllCardsUseCase,
    private val _allCardsState: MutableStateFlow<AllCardsState> = MutableStateFlow(AllCardsState.Empty),
) : ViewModel() {

    val allCardsState: StateFlow<AllCardsState>
        get() = _allCardsState

    fun getAllCards() {
        viewModelScope.launch {
            getAllCardsUseCase().catch {
            }.collect {
            }
        }
    }
}
