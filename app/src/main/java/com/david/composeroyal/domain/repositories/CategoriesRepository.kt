package com.david.composeroyal.domain.repositories

import com.david.composeroyal.domain.models.CategoriesModel
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun getAllCategories(): Flow<List<CategoriesModel>>
}
