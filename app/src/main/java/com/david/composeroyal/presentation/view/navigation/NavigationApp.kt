package com.david.composeroyal.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.david.composeroyal.presentation.view.ui.categoriesList.HomeScreen
import com.david.composeroyal.presentation.view.ui.categoryDetail.ArtistDetailScreen
import com.david.composeroyal.presentation.view.ui.categoryDetail.CategoryDetailScreen
import com.david.composeroyal.presentation.view.ui.favorites.FavoriteScreen
import com.david.composeroyal.presentation.view.ui.profile.ProfileScreen

@Composable
fun NavigationApp(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainRoutes.HOME_NAVIGATION.key,
    ) {
        categoryNavigation(navController)
        favoriteNavigation(navController)
        profileNavigation(navController)
    }
}

fun NavGraphBuilder.categoryNavigation(
    navController: NavController,
) {
    navigation(
        startDestination = NavigationRoutes.Categories.route,
        route = MainRoutes.HOME_NAVIGATION.key,
    ) {
        composable(NavigationRoutes.Categories) {
            HomeScreen { id, name ->
                navController.navigate(NavigationRoutes.CategoryDetail.createRoute(id, name))
            }
        }
        composable(NavigationRoutes.CategoryDetail) {
            CategoryDetailScreen {
                navController.navigate(NavigationRoutes.ArtistDetail.createRoute(it))
            }
        }
        composable(NavigationRoutes.ArtistDetail) {
            ArtistDetailScreen()
        }
    }
}

fun NavGraphBuilder.favoriteNavigation(
    navController: NavController,
) {
    navigation(
        startDestination = NavigationRoutes.Favorite.route,
        route = MainRoutes.FAVORITE_NAVIGATION.key,
    ) {
        composable(NavigationRoutes.Favorite) {
            FavoriteScreen()
        }
    }
}

fun NavGraphBuilder.profileNavigation(
    navController: NavController,
) {
    navigation(
        startDestination = NavigationRoutes.Profile.route,
        route = MainRoutes.PROFILE_NAVIGATION.key,
    ) {
        composable(NavigationRoutes.Profile) {
            ProfileScreen()
        }
    }
}

private fun NavGraphBuilder.composable(
    navRoute: NavigationRoutes,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(navRoute.route, navRoute.args) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavigationArgs): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}
