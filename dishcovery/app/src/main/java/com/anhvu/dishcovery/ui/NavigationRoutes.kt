package com.anhvu.dishcovery.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import com.anhvu.dishcovery.uikit.R

sealed class NavigationRoutes(
    val route: String,
    val resourceId: Int,
    val icon: ImageVector
) {
    object Home :
        NavigationRoutes("home", R.string.title_home, Icons.Filled.Home)

    object Dashboard : NavigationRoutes(
        "dashboard",
        R.string.title_dashboard,
        Icons.Filled.Info
    )

    object Notifications :
        NavigationRoutes(
            "notifications",
            R.string.title_notifications,
            Icons.Filled.Notifications
        )

    companion object {
        val allRoutes = listOf(Home, Dashboard, Notifications)
    }
}