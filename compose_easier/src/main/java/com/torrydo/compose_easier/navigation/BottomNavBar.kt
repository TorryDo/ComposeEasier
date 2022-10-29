package com.torrydo.compose_easier.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.torrydo.compose_easier.navigation.ext.BottomNavItem

/**
 * navigate between set of bottomNavItem using bottomNavBar, this is a subclass of 'Nav'
 * */
class BottomNavBar(
    navHostController: NavHostController,
    private val bottomNavItems: List<BottomNavItem>
) : Nav(navHostController, bottomNavItems) {

    /**
     * return a bottom nav bar, render will run first before SetupNavGraph
     * */
    @Composable
    fun Render(
        backgroundColor: Color = MaterialTheme.colors.secondary,
        contentColor: Color = MaterialTheme.colors.onSecondary
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination


        BottomNavigation(backgroundColor = backgroundColor, contentColor = contentColor) {
            bottomNavItems.forEach { screen ->
                BottomNavItem(
                    itemHomeBottom = screen,
                    currentDestination = currentDestination,
                    navController = navHostController
                )
            }
        }
    }

}

open class BottomNavItem(
    route: String,
    title: String,
    val icon: ImageVector,
) : NavItem(route, title)


//@Composable
//fun NavGraphBuilder.nestedBottomNavBar(
//    navHostController: NavHostController,
//    route: String,
//    startDestination: String,
//    bottomNavItems: List<BottomNavItem>,
//    screen: @Composable (route: String) -> Unit
//) {
//
//    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    navigation(startDestination = startDestination, route = route) {
//
//        BottomNavigation {
//            bottomNavItems.forEach { screen ->
//                BottomNavItem(
//                    itemHomeBottom = screen,
//                    currentDestination = currentDestination,
//                    navController = navHostController
//                )
//            }
//        }
//
//
//    }

