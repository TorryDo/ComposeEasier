package com.torrydo.compose_easier.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object CheckBoxEz{

    @Composable
    fun RoundedCorner(
        modifier: Modifier = Modifier,
        borderWidth: Dp = 2.dp,
        borderColor: Color = Color.LightGray,
        checkedBackgroundColor: Color = MaterialTheme.colors.primary,
        cornerRadius: Dp = 10.dp,
        iconColor: Color = Color.White,
        checked: Boolean,
        enabled: Boolean = true,
        onCheckedChange: (Boolean) -> Unit
    ) {
        val _background = if (checked) checkedBackgroundColor else Color.Transparent
        val _borderWidth = if (checked) 0.dp else borderWidth
        val _borderColor = if (checked) Color.Transparent else borderColor

        val _roundedCornerShape = RoundedCornerShape(cornerRadius)

        Box(
            modifier = Modifier
                .size(28.dp)
                .then(modifier)
                .border(BorderStroke(_borderWidth, _borderColor), shape = _roundedCornerShape)
                .clip(_roundedCornerShape)
                .background(_background),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { onCheckedChange(checked.not()) },
                modifier = Modifier.fillMaxSize(),
                enabled = enabled
            ) {

                if (checked)
                    Icon(
                        imageVector = Icons.Filled.Check,
                        tint = iconColor,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(_background),
                        contentDescription = "rounded corner checkbox"
                    )
            }
        }
    }
}
