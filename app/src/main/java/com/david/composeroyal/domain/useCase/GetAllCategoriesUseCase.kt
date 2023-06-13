package com.david.composeroyal.domain.useCase

import com.david.composeroyal.data.models.categories.mapToDomain
import com.david.composeroyal.domain.common.retryUpdateToken
import com.david.composeroyal.domain.models.CategoriesModel
import com.david.composeroyal.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val tokenUseCase: UpdateAccessTokenUseCase,
) {
    operator fun invoke(): Flow<List<CategoriesModel>> =
        categoriesRepository.getAllCategories().map { cardResponses ->
            cardResponses.map {
                it.mapToDomain()
            }
        }.retryUpdateToken(tokenUseCase)
}
