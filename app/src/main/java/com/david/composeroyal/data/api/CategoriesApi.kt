package com.david.composeroyal.data.api

import com.david.composeroyal.data.api.common.ApiRoutes
import com.david.composeroyal.data.models.categories.CategoriesItems
import com.david.composeroyal.data.network.KtorHttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class CategoriesApi(
    private val httpClient: KtorHttpClient,
) {
    suspend fun getAllCategories(): Result<CategoriesItems> {
        return try {
            val response = httpClient.getHttpClient().get {
                url(ApiRoutes.CATEGORIES_PATH)
            }
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
