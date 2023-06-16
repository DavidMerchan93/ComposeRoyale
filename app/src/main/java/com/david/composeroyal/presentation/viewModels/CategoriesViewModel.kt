package com.david.composeroyal.presentation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.composeroyal.domain.useCase.GetAllCategoriesUseCase
import com.david.composeroyal.presentation.states.CategoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
) : ViewModel() {

    var allCategoriesState by mutableStateOf(CategoriesState(loading = true))
        private set

    init {
        getAllCards()
    }

    private fun getAllCards() {
        allCategoriesState = CategoriesState(loading = true)

        getAllCategoriesUseCase().catch {
            allCategoriesState = CategoriesState(isError = true)
        }.map { cards ->
            allCategoriesState = CategoriesState(cardsCollection = cards)
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}
