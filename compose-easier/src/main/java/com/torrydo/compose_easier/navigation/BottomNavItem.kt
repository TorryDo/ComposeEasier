package com.torrydo.compose_easier.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController


open class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
)

@Composable
fun RowScope.BottomNavItemBuilder(
    itemHomeBottom: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    fun isNavSelected() =
        currentDestination?.hierarchy?.any { it.route == itemHomeBottom.route } == true


    fun onNavItemClicked() = navController.navigate(itemHomeBottom.route) {
        popUpTo(0)
    }


    BottomNavigationItem(
        selected = isNavSelected(),
        label = {
            Text(text = itemHomeBottom.title)
        },
        icon = {
            Icon(
                imageVector = itemHomeBottom.icon,
                contentDescription = "Navigation Icon"
            )
        },
        onClick = { onNavItemClicked() }

    )
}