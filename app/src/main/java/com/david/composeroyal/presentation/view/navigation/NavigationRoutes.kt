package com.david.composeroyal.presentation.view.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavigationRoutes(
    val baseRoute: String,
    private val navigationArgs: List<NavigationArgs> = emptyList(),
) {
    val route: String = run {
        val argKeys = navigationArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navigationArgs.map {
        navArgument(it.key) {
            type = it.type
        }
    }

    object Home : NavigationRoutes(HOME_PATH)
    object CategoryDetail : NavigationRoutes(
        baseRoute = CATEGORY_DETAIL_PATH,
        navigationArgs = listOf(NavigationArgs.CATEGORY_ID, NavigationArgs.CATEGORY_NAME),
    ) {
        fun createRoute(id: String, name: String): String {
            return "$baseRoute/$id/$name"
        }
    }
}

enum class NavigationArgs(val key: String, val type: NavType<*> = NavType.StringType) {
    CATEGORY_ID("categoryId", NavType.StringType),
    CATEGORY_NAME("categoryName", NavType.StringType),
}

private const val HOME_PATH = "home"
private const val CATEGORY_DETAIL_PATH = "categoryDetail"
