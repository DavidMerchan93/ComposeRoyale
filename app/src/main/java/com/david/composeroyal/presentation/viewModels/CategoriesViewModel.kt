package com.david.composeroyal.presentation.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    private val _allCategoriesState: MutableState<CategoriesState> =
        mutableStateOf(CategoriesState.Empty)

    val allCategoriesState: MutableState<CategoriesState>
        get() = _allCategoriesState

    init {
        getAllCards()
    }

    private fun getAllCards() {
        _allCategoriesState.value = CategoriesState.Loading

        getAllCategoriesUseCase().catch {
            _allCategoriesState.value = CategoriesState.Error
        }.map { cards ->
            _allCategoriesState.value = CategoriesState.Success(cards)
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}
