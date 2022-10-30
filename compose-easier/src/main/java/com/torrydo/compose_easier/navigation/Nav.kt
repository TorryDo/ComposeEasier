package com.torrydo.compose_easier.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.torrydo.compose_easier.navigation.ext.offTo
import com.torrydo.compose_easier.navigation.ext.to
import kotlinx.coroutines.CoroutineScope

/**
 * navigation between set of NavItem
 * */
open class Nav(
    internal val navHostController: NavHostController,
    private val navItems: List<NavItem>
) {

    companion object {
        val navData = NavData()
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    open fun SetupNavGraph(
        onNavChange: ((route: String) -> Unit)? = null,
        startDestination: String = navItems.first().route,
        screen: @Composable (route: String, navHostController: NavHostController, navData: NavData) -> Unit,
    ) {

        AnimatedNavHost(
            navController = navHostController,
            startDestination = startDestination,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            navItems.forEach { navItem ->
                composable(
                    route = navItem.route,
                ) {

                    screen(navItem.route, navHostController, navData)

                }
            }
        }
    }

    private fun getCurrentRoute(): String? = navHostController.currentDestination?.route

    fun getNavHostController() = navHostController

    /**
     * navigate to other screen
     * */
    fun to(
        scope: CoroutineScope,
        route: String,
        data: Any? = null,
        beforeNavigation: (suspend () -> Unit)? = null
    ) {

        navHostController.to(
            scope = scope,
            route = route,
            beforeNavigation = beforeNavigation,
            data = data
        )

    }

    /**
     * navigate to another screen without add current screen to backstack
     * */
    fun offTo(
        scope: CoroutineScope,
        route: String,
        data: Any? = null,
        beforeNavigation: (suspend () -> Unit)? = null
    ) {
        navHostController.offTo(
            scope = scope,
            route = route,
            beforeNavigation = beforeNavigation,
            data = data
        )

    }

}

data class NavData(
    var data: Any? = null
) {
    fun <T> get(): T {

        return try {
            data as T
        } catch (e: Exception) {
            throw TypeCastException("destination's expected data is not matched with passed data")
        }

    }

}

open class NavItem(
    val route: String,
    val title: String,
)


