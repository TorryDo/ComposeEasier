package com.torrydo.compose_easier.navigation.ext

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.torrydo.compose_easier.navigation.BottomNavItem

@Composable
fun RowScope.BottomNavItem(
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