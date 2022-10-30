package com.torrydo.compose_easier.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.torrydo.compose_easier.ext.noRippleClickable

object IconEz{

    private val DEFAULT_CLICK_ZONE_SIZE = 48.dp

    @Composable
    fun Clickable(
        icon: ImageVector,
        contentDescription: String = "clickable icon",
        onClick: () -> Unit = {}
    ) {
        IconButton(onClick = onClick) {
            Icon(icon, contentDescription, tint = MaterialTheme.colors.onSecondary)
        }
    }

    @Composable
    inline fun Clickable(
        modifier: Modifier = Modifier,
        crossinline icon: @Composable () -> Unit,
        crossinline onClick: () -> Unit = {}
    ) {
        IconButton(modifier = modifier, onClick = { onClick() }) {
            icon()
        }
    }

    @Composable
    fun Static(
        modifier: Modifier = Modifier.size(DEFAULT_CLICK_ZONE_SIZE),
        icon: @Composable () -> Unit,
        onClick: () -> Unit = {}
    ) {
        Box(
            modifier = modifier.noRippleClickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
    }



}

