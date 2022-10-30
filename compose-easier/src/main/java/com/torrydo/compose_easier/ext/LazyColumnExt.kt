package com.torrydo.compose_easier.ext

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*

@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }

    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }

        }
    }.value
}

@Composable
fun LazyListState.listenScrollDirection(
    onScrollUp: () -> Unit,
    onScrollDown: () -> Unit
) {

    val isScrollingUp = this.isScrollingUp()

    LaunchedEffectWith(isScrollingUp) {
        if(it){
            onScrollUp()
        }else{
            onScrollDown()
        }
    }

}