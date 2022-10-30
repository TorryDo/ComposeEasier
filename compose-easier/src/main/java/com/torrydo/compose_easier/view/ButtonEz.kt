package com.torrydo.compose_easier.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


object ButtonEz {

    @Composable
    fun Flat(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        elevation: ButtonElevation? = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        shape: Shape = MaterialTheme.shapes.small, // RoundedCornerShape(25)
        colors: ButtonColors = ButtonDefaults.buttonColors(),
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        content: @Composable RowScope.() -> Unit
    ) {

        Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = ButtonDefaults.elevation(),
            shape = shape,
            colors = colors,
            contentPadding = contentPadding,
            content = content
        )
    }

    @Composable
    fun Outline(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        elevation: ButtonElevation? = null,
        shape: Shape = RoundedCornerShape(25),
        border: BorderStroke = BorderStroke((1.5).dp, color = MaterialTheme.colors.primary),
        colors: ButtonColors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        content: @Composable RowScope.() -> Unit
    ) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = elevation,
            shape = shape,
            border = border,
            colors = colors,
            contentPadding = contentPadding,
            content = content
        )
    }

    @Composable
    fun Gradient(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        gradient: Brush = Brush.horizontalGradient(listOf(Color.Gray, Color.Cyan)),
        shape: Shape = RoundedCornerShape(15),

        content: @Composable RowScope.() -> Unit
    ) {

        if (enabled) {
            Row(
                modifier = modifier
                    .clip(shape = shape)
                    .background(gradient)
                    .then(modifier)
                    .clickable { onClick() }
                    .padding(vertical = 10.dp),
                content = content
            )
        } else {
            Row(
                modifier = modifier
                    .clip(shape = shape)
                    .background(Color.Gray.copy(alpha = 0.4f))
                    .padding(vertical = 10.dp),
                content = content
            )
        }

    }
}