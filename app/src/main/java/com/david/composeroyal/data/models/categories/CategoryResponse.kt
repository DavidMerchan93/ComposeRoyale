package com.david.composeroyal.data.models.categories

import com.david.composeroyal.domain.models.CategoriesModel
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesItems(
    val categories: CategoryResponse?,
)

@Serializable
data class CategoryResponse(
    val href: String,
    val items: List<Category>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val total: Int,
)

@Serializable
data class Category(
    val id: String,
    val name: String,
    val href: String,
    val icons: List<Icon>,
) {
    @Serializable
    data class Icon(
        val height: Int? = 0,
        val url: String? = String(),
        val width: Int? = 0,
    )
}

fun Category.mapToDomain(): CategoriesModel = CategoriesModel(
    id = id,
    name = name,
    href = href,
    icon = icons.firstOrNull()?.url ?: String(),
)
