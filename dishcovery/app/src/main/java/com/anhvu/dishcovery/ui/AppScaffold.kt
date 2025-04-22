package com.anhvu.dishcovery.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun AppScaffold(
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(routes = NavigationRoutes.allRoutes)
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}