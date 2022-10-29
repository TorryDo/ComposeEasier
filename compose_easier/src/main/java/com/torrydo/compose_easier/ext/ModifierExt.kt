package com.torrydo.compose_easier.ext


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp


inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit) =
    composed {
        clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }

fun Modifier.leftRoundedCorner(dp: Dp): Modifier {
    return clip(
        RoundedCornerShape(
            topEnd = dp,
            bottomEnd = dp
        )
    )
}

fun Modifier.topRoundedCorner(dp: Dp): Modifier {
    return clip(
        RoundedCornerShape(
            topStart = dp,
            topEnd = dp,
        )
    )
}

fun Modifier.dashedBorder(width: Dp, radius: Dp, color: Color) =
    drawBehind {
        drawIntoCanvas {
            val paint = Paint()
                .apply {
                    strokeWidth = width.toPx()
                    this.color = color
                    style = PaintingStyle.Stroke
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
                }

//            it.drawRoundRect(
//                width.toPx(),
//                width.toPx(),
//                size.width - width.toPx(),
//                size.height - width.toPx(),
//                radius.toPx(),
//                radius.toPx(),
//                paint
//            )

            it.drawRoundRect(
                (width.toPx() / 2),
                (width.toPx() / 2),
                size.width - (width.toPx() / 2),
                size.height - (width.toPx() / 2),
                radius.toPx(),
                radius.toPx(),
                paint
            )
        }
    }