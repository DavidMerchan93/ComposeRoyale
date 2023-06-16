package com.david.composeroyal.data.repositories

import com.david.composeroyal.data.api.CategoriesApi
import com.david.composeroyal.data.models.categories.mapToDomain
import com.david.composeroyal.domain.models.CategoriesModel
import com.david.composeroyal.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val categoriesApi: CategoriesApi,
) : CategoriesRepository {
    override fun getAllCategories(): Flow<List<CategoriesModel>> = flow {
        val result = categoriesApi.getAllCategories()
        when {
            result.isSuccess -> {
                val categories = result.getOrNull()?.categories?.items?.map {
                    it.mapToDomain()
                } ?: emptyList()
                emit(categories)
            }

            else -> {
                throw result.exceptionOrNull() ?: Throwable("Error al obtener la lista")
            }
        }
    }
}
