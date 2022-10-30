package com.torrydo.compose_easier.view

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

object TextFieldEz {

    @Composable
    fun EditText(
        value: String,
        modifier: Modifier = Modifier,
        textStyle: TextStyle = MaterialTheme.typography.body1,
        onDone: (KeyboardActionScope.() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        placeHolderText: @Composable () -> Unit,
        onValueChange: (String) -> Unit
    ) {

        SimpleTextField(
            modifier = modifier,
            textStyle = textStyle,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            placeholderText = placeHolderText,
            value = value,
            contentModifier = Modifier.padding(horizontal = 10.dp),
            trailingIcon = trailingIcon,
            singleLine = true,
            onValueChange = onValueChange,
            keyboardActions = KeyboardActions(
                onDone = onDone
            ),

            )
    }

    @Composable
    fun SimpleTextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle = LocalTextStyle.current,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions(),
        singleLine: Boolean = false,
        maxLines: Int = Int.MAX_VALUE,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        fontSize: TextUnit = MaterialTheme.typography.body2.fontSize,
        onTextLayout: (TextLayoutResult) -> Unit = {},
        cursorBrush: Brush = SolidColor(Color.Black),

        placeholderText: @Composable () -> Unit,
        contentModifier: Modifier
    ) {
        BasicTextField(modifier = modifier
            .fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            maxLines = maxLines,
            enabled = enabled,
            readOnly = readOnly,
            interactionSource = interactionSource,
            textStyle = textStyle,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            onTextLayout = onTextLayout,
            cursorBrush = cursorBrush,
            decorationBox = { innerTextField ->
                Row(
                    modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) leadingIcon()
                    Box(
                        contentModifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) placeholderText()
                        innerTextField()
                    }
                    if (trailingIcon != null) trailingIcon()
                }
            }
        )
    }
}