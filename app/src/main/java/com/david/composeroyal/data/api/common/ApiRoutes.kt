package com.david.composeroyal.data.api.common

object ApiRoutes {
    private const val BASE_ACCOUNT_URL = "https://accounts.spotify.com"
    private const val BASE_URL = "https://api.spotify.com"

    const val TOKEN_PATH = "$BASE_ACCOUNT_URL/api/token"
    const val CATEGORIES_PATH = "$BASE_URL/v1/browse/categories"
    const val SEARCH_PATH = "$BASE_URL/v1/search"
}
