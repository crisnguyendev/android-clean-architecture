package com.anhvu.dishcovery.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    routes: List<NavigationRoutes>,
    navController: NavController? = null
) {
    NavigationBar {
        val navBackStackEntry by navController?.currentBackStackEntryAsState() ?: return@NavigationBar
        val currentDestination = navBackStackEntry?.destination
        routes.forEach { route ->
            NavigationBarItem(
                icon = { Icon(route.icon, contentDescription = null) },
                label = { Text(stringResource(route.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == route.route } == true,
                onClick = {
                    navController?.navigate(route.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}