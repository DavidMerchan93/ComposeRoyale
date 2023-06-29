package com.david.composeroyal.presentation.view.ui.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.david.composeroyal.presentation.view.navigation.MainRoutes

@Composable
fun HomeBottomNavigation(currentRoute: String, navigationClick: (item: MainRoutes) -> Unit) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.secondary
    ) {
        MainRoutes.values().forEach { item ->
            BottomNavigationItem(
                selected = currentRoute.contains(item.key),
                onClick = {
                    navigationClick(item)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.key)
                },
                label = {
                    Text(text = item.key)
                },
            )
        }
    }
}
