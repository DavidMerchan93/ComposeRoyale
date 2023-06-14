package com.david.composeroyal.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.david.composeroyal.presentation.states.CategoriesState
import com.david.composeroyal.presentation.view.categoryDetail.CategoryDetailScreen
import com.david.composeroyal.presentation.view.home.HomeScreen

@Composable
fun NavigationApp(categoriesState: MutableState<CategoriesState>) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Home.route,
    ) {
        composable(NavigationRoutes.Home) {
            HomeScreen(categoriesState = categoriesState) { id, name ->
                navController.navigate(NavigationRoutes.CategoryDetail.createRoute(id, name))
            }
        }
        composable(NavigationRoutes.CategoryDetail) { navBackStack ->
            CategoryDetailScreen(
                id = navBackStack.findArg(NavigationArgs.CATEGORY_ID),
                name = navBackStack.findArg(NavigationArgs.CATEGORY_NAME),
            )
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
