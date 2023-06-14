package com.david.composeroyal.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
        composable(NavigationRoutes.Home.route) {
            HomeScreen(categoriesState = categoriesState) { id, name ->
                navController.navigate(NavigationRoutes.CategoryDetail.createRoute(id, name))
            }
        }
        composable(
            NavigationRoutes.CategoryDetail.route,
            NavigationRoutes.CategoryDetail.args,
        ) { navBackStack ->
            val id = navBackStack.arguments?.getString(NavigationArgs.CATEGORY_ID.key)
            val name =
                navBackStack.arguments?.getString(NavigationArgs.CATEGORY_NAME.key)
            requireNotNull(id)
            requireNotNull(name)
            CategoryDetailScreen(id = id, name = name)
        }
    }
}
