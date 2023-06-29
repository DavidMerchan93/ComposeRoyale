package com.david.composeroyal.presentation.view.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.david.composeroyal.R
import com.david.composeroyal.presentation.theme.ComposeRoyalTheme
import com.david.composeroyal.presentation.theme.Secondary
import com.david.composeroyal.presentation.view.navigation.MainRoutes
import com.david.composeroyal.presentation.view.navigation.NavigationApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navStackEntry?.destination?.route ?: MainRoutes.HOME_NAVIGATION.key

            ComposeRoyalTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Secondary),
                            title = {
                                Text(
                                    text = stringResource(R.string.app_name),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleLarge,
                                )
                            },
                        )
                    },
                    bottomBar = {
                        HomeBottomNavigation(currentRoute) { item ->
                            navController.navigate(item.key) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        }
                    },
                    content = { paddingValues ->
                        Surface(modifier = Modifier.padding(paddingValues)) {
                            NavigationApp(navController)
                        }
                    },
                )
            }
        }
    }
}
