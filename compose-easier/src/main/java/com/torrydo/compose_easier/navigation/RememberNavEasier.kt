package com.torrydo.compose_easier.navigation

import android.content.Context
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberNavEasier(routes: List<String>): NavEasier {
    val context = LocalContext.current
    val animatedNavController = rememberAnimatedNavController()
    return remember { NavEasier(context, animatedNavController, routes) }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberBottomNavEasier(routes: List<BottomNavItem>): BottomNavEasier {
    val context = LocalContext.current
    val animatedNavController = rememberAnimatedNavController()
    return remember { BottomNavEasier(context, animatedNavController, routes) }
}


open class BottomNavEasier
internal constructor(
    context: Context,
    navHostController: NavHostController,
    private val items: List<BottomNavItem>
) : NavEasier(context, navHostController, items.map { it.route }) {
    @Composable
    fun BottomBar(
        backgroundColor: Color = MaterialTheme.colors.primary,
        contentColor: Color = MaterialTheme.colors.onPrimary,
    ) {
        val navBackStackEntry by this.navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        BottomNavigation(backgroundColor = backgroundColor, contentColor = contentColor) {
            items.forEach { item ->
                BottomNavItemBuilder(
                    itemHomeBottom = item,
                    currentDestination = currentDestination,
                    navController = navHostController
                )
            }
        }
    }

    @Composable
    fun BottomBarBuilder(
        backgroundColor: Color = MaterialTheme.colors.primary,
        contentColor: Color = MaterialTheme.colors.onPrimary,
        builder: @Composable RowScope.(route: BottomNavItem, navData: NavData) -> Unit,
    ) {
        BottomNavigation(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ) {
            items.forEach { item ->
                builder(item, navData)
            }
        }
    }

}

open class NavEasier
internal constructor(
    context: Context,
    internal val navHostController: NavHostController,
    private val routes: List<String>
) : NavController(context) {

    companion object {
        internal val navData = NavData()
    }

    private lateinit var scope: CoroutineScope
    private var onChangeListener: ((route: String) -> Unit)? = null

    // composable ----------------------------------------------------------------------------------
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun NavHost(
        modifier: Modifier = Modifier,
        startDestination: String = routes.first(),
        builder: @Composable (route: String, navData: NavData) -> Unit,
    ) {
        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            this@NavEasier.scope = scope
        }

        AnimatedNavHost(
            modifier = modifier,
            navController = navHostController,
            startDestination = startDestination,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            routes.forEach { route ->
                composable(
                    route = route,
                ) {
                    builder(route, navData)
                }
            }
        }
    }

    // support function ----------------------------------------------------------------------------
    fun getCurrentRoute(): String? = navHostController.currentDestination?.route
    fun getNavHostController() = navHostController

    @Deprecated("not finished")
    fun setOnChangeListener(onChange: (route: String) -> Unit) {
        onChangeListener = onChange
    }

    // addons --------------------------------------------------------------------------------------
    open fun pushNamed(route: String, data: Any? = null) {
        scope.launch {
            navData.data = data
            navHostController.navigate(route)
        }
    }

    open fun pop() {
        scope.launch {
            navHostController.popBackStack()
        }
    }

    open fun popAndPushNamed(route: String, data: Any? = null) {
        scope.launch {
            navData.data = data
            navHostController.navigate(route = route) {
                popUpTo(currentBackStackEntry!!.destination.route!!) {
                    inclusive = true
                }
            }
        }
    }


    // override ------------------------------------------------------------------------------------
    final override fun setLifecycleOwner(owner: LifecycleOwner) {
        super.setLifecycleOwner(owner)
    }

    final override fun setOnBackPressedDispatcher(dispatcher: OnBackPressedDispatcher) {
        super.setOnBackPressedDispatcher(dispatcher)
    }

    final override fun enableOnBackPressed(enabled: Boolean) {
        super.enableOnBackPressed(enabled)
    }

    final override fun setViewModelStore(viewModelStore: ViewModelStore) {
        super.setViewModelStore(viewModelStore)
    }
}