package com.torrydo.compose_easier.navigation.ext

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.torrydo.compose_easier.navigation.Nav
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * navigate to other screen
 * */
fun NavController.to(
    scope: CoroutineScope,
    route: String,
    data: Any? = null,
    beforeNavigation: (suspend () -> Unit)? = null
) {

    Nav.navData.data = data

    scope.launch {
        beforeNavigation?.invoke()
        this@to.navigate(route)
    }
}

/**
 * navigate to another screen without add current screen to backstack
 * */
fun NavController.offTo(
    scope: CoroutineScope,
    route: String,
    data: Any? = null,
    beforeNavigation: (suspend () -> Unit)? = null
) {

    Nav.navData.data = data

    scope.launch {

        beforeNavigation?.invoke()
        this@offTo.navigate(route) {
            popUpTo(currentBackStackEntry!!.destination.route!!) {
                inclusive = true
            }
        }
    }
}

fun NavHostController.getCurrentRoute(): String? {
    return this.currentBackStackEntry?.destination?.route
}
