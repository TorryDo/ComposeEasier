package com.torrydo.compose_easier.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.torrydo.compose_easier.ext.noRippleClickable

object SwitchEz {

    @Composable
    fun Fill(
        isOn: Boolean,
        onChange: ((Boolean) -> Unit)? = null,
        width: Dp = 36.dp,
        height: Dp = 20.dp,
        checkedTrackColor: Color = MaterialTheme.colors.primary,
        uncheckedTrackColor: Color = Color.Gray,
        gapBetweenThumbAndTrackEdge: Dp = 3.dp,
        pointerColor: Color = Color.White
    ) {

        val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

        // To move thumb, we need to calculate the position (along x axis)
        val animatePosition = animateFloatAsState(
            targetValue = if (isOn)
                with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
            else
                with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
        )

        Canvas(
            modifier = Modifier
                .width(width)
                .height(height)
                .noRippleClickable {
                    onChange?.invoke(isOn.not())
                }
        ) {
            // Track
            drawRoundRect(
                color = if (isOn) checkedTrackColor else uncheckedTrackColor,
                cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            )

            // Thumb
            drawCircle(
                color = pointerColor,
                radius = thumbRadius.toPx(),
                center = Offset(
                    x = animatePosition.value,
                    y = size.height / 2
                )
            )
        }
    }

    @Composable
    fun Border(
        isOn: Boolean,
        onChange: ((Boolean) -> Unit)? = null,
        width: Dp = 36.dp,
        height: Dp = 20.dp,
        strokeWidth: Dp = 2.dp,
        checkedTrackColor: Color = MaterialTheme.colors.primary,
        uncheckedTrackColor: Color = Color.Gray,
        gapBetweenThumbAndTrackEdge: Dp = 3.dp
    ) {

        val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

        // To move thumb, we need to calculate the position (along x axis)
        val animatePosition = animateFloatAsState(
            targetValue = if (isOn)
                with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
            else
                with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
        )

        Canvas(
            modifier = Modifier
                .width(width)
                .height(height)
                .noRippleClickable {
                    onChange?.invoke(isOn.not())
                }
        ) {
            // Track
            drawRoundRect(
                color = if (isOn) checkedTrackColor else uncheckedTrackColor,
                cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
                style = Stroke(width = strokeWidth.toPx())
            )

            // Thumb
            drawCircle(
                color = if (isOn) checkedTrackColor else uncheckedTrackColor,
                radius = thumbRadius.toPx(),
                center = Offset(
                    x = animatePosition.value,
                    y = size.height / 2
                )
            )
        }
    }
}