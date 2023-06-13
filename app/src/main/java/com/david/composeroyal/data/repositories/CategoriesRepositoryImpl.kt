package com.david.composeroyal.data.repositories

import com.david.composeroyal.data.api.CategoriesApi
import com.david.composeroyal.data.models.categories.Category
import com.david.composeroyal.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesApi: CategoriesApi,
) : CategoriesRepository {
    override fun getAllCategories(): Flow<List<Category>> = flow {
        val result = categoriesApi.getAllCategories()
        when {
            result.isSuccess -> {
                emit(result.getOrNull()?.categories?.items ?: emptyList())
            }

            else -> {
                throw result.exceptionOrNull() ?: Throwable("Error al obtener la lista")
            }
        }
    }
}
