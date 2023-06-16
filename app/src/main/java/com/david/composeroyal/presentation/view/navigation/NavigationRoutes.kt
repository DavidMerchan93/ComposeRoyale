package com.david.composeroyal.presentation.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavigationRoutes(
    val mainRoute: MainRoutes,
    val baseRoute: String,
    private val navigationArgs: List<NavigationArgs> = emptyList(),
) {
    val route: String = run {
        val argKeys = navigationArgs.map { "{${it.key}}" }
        listOf(mainRoute.key, baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navigationArgs.map {
        navArgument(it.key) {
            type = it.type
        }
    }

    object Categories : NavigationRoutes(
        mainRoute = MainRoutes.HOME_NAVIGATION,
        baseRoute = CATEGORIES_PATH,
    )

    object CategoryDetail : NavigationRoutes(
        mainRoute = MainRoutes.HOME_NAVIGATION,
        baseRoute = CATEGORY_DETAIL_PATH,
        navigationArgs = listOf(NavigationArgs.CATEGORY_ID, NavigationArgs.CATEGORY_NAME),
    ) {
        fun createRoute(id: String, name: String): String {
            return listOf(
                mainRoute.key,
                baseRoute,
                id,
                name,
            ).joinToString("/")
        }
    }

    object Favorite : NavigationRoutes(
        mainRoute = MainRoutes.FAVORITE_NAVIGATION,
        baseRoute = FAVORITE_PATH,
    )

    object Profile : NavigationRoutes(
        mainRoute = MainRoutes.PROFILE_NAVIGATION,
        baseRoute = PROFILE_PATH,
    )
}

enum class MainRoutes(val key: String, val icon: ImageVector) {
    HOME_NAVIGATION("home", Icons.Default.Home),
    FAVORITE_NAVIGATION("favorite", Icons.Default.Favorite),
    PROFILE_NAVIGATION("profile", Icons.Default.Person),
}

enum class NavigationArgs(val key: String, val type: NavType<*> = NavType.StringType) {
    CATEGORY_ID("categoryId", NavType.StringType),
    CATEGORY_NAME("categoryName", NavType.StringType),
}

private const val CATEGORIES_PATH = "categories"
private const val CATEGORY_DETAIL_PATH = "categoryDetail"
private const val FAVORITE_PATH = "favoriteList"
private const val PROFILE_PATH = "userProfile"
