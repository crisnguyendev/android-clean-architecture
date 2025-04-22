package com.anhvu.dishcovery

import SearchRecipeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anhvu.dishcovery.ui.NavigationRoutes

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Home.route,
        modifier = modifier
    ) {
        composable(NavigationRoutes.Home.route) { SearchRecipeScreen() }
        composable(NavigationRoutes.Dashboard.route) {
            Text(
                text = "Dashboard Screen",
                modifier = Modifier.fillMaxSize(),
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
        composable(NavigationRoutes.Notifications.route) {
            androidx.compose.material3.Text(
                text = "Notifications Screen",
                modifier = Modifier.fillMaxSize(),
                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}