package com.torrydo.compose_easier.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.torrydo.compose_easier.ext.LaunchedEffectWith
import com.torrydo.compose_easier.ext.noRippleClickable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private val DURATION = 300
private val DURATIONL = DURATION.toLong()

private val DIMMER = 0.5f
private val WIDTH = 250.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NavDrawer(
    modifier: Modifier = Modifier,
    isDrawerOpen: MutableState<Boolean>,
    duration: Int = DURATION,
    dimmer: Float = DIMMER,

    onAnimClose: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()

    var isOpen by remember { mutableStateOf(false) }

    val onRequestClose = {
        scope.launch {
            isOpen = false
            onAnimClose()

            delay(duration.toLong())

            isDrawerOpen.value = false
        }

    }

    if (isDrawerOpen.value) {

        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth = false),
            onDismissRequest = { onRequestClose() }
        ) {

            val easingWidth by animateDpAsState(
                targetValue = if (isOpen) 0.dp else WIDTH,
                animationSpec = tween(
                    durationMillis = DURATION,
                    easing = FastOutSlowInEasing
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .noRippleClickable { }
                ) {
                    Box(
                        modifier = Modifier
                            .offset(x = -easingWidth)
                            .fillMaxHeight()

                    ) {
                        Column(modifier = modifier) {
                            content()
                        }
                    }
                }

                Box(modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .noRippleClickable { onRequestClose() }
                )
            }


        }

    }

    LaunchedEffectWith(isDrawerOpen.value) {
        if (!it) return@LaunchedEffectWith
        isOpen = true
    }
}

// item -------------------------------------------------------------------------

@Composable
fun NavDrawerItem(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    body: @Composable () -> Unit,
    tail: (@Composable () -> Unit)? = null,
) {


    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Box(
            modifier = Modifier
                .weight(0.2f)
        ) {
            header?.invoke()
        }
        Box(modifier = Modifier
            .weight(0.6f)
//            .clickable { onClick?.invoke() }
        ) {
            body()
        }
        Box(
            modifier = Modifier.weight(0.2f)
        ) {
            tail?.invoke()
        }

    }
}