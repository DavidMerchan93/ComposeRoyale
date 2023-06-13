package com.david.composeroyal.domain.repositories

import com.david.composeroyal.data.models.categories.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    fun getAllCategories(): Flow<List<Category>>
}
