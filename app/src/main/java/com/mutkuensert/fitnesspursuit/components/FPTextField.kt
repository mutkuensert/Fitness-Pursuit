package com.mutkuensert.fitnesspursuit.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mutkuensert.fitnesspursuit.core.getStringRes
import com.mutkuensert.fitnesspursuit.resources.TextResKeys
import com.mutkuensert.fitnesspursuit.ui.theme.appTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FPTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    placeholder: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    borderEnabled: Boolean = true,
    isBorderError: Boolean = false,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Color.Gray,
        focusedBorderColor = Color.DarkGray
    ),
    shape: Shape = TextFieldDefaults.OutlinedTextFieldShape,
    focusedBorderThickness: Dp = 1.dp,
    unfocusedBorderThickness: Dp = 0.5.dp

) {
    var border by remember { mutableStateOf(1.dp) }
    val textStyle = MaterialTheme.appTypography.bodyM

    BasicTextField(
        modifier = Modifier
            .onFocusChanged { focusState -> border = if (focusState.hasFocus) 2.dp else 1.dp }
            .then(modifier),
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        textStyle = textStyle,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                enabled = enabled,
                interactionSource = interactionSource,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                placeholder = {
                    if (placeholder != null) {
                        Text(
                            text = getStringRes(TextResKeys.KG),
                            style = textStyle,
                            color = Color.DarkGray
                        )
                    }
                },
                border = {
                    if (borderEnabled) {
                        TextFieldDefaults.BorderBox(
                            enabled = enabled,
                            interactionSource = interactionSource,
                            isError = isBorderError,
                            colors = colors,
                            shape = shape,
                            focusedBorderThickness = focusedBorderThickness,
                            unfocusedBorderThickness = unfocusedBorderThickness
                        )
                    }
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewFPTextField() {
    Column {
        val (value, onValueChange) = remember { mutableStateOf("") }

        FPTextField(value = value, placeholder = "Placeholder", onValueChange = onValueChange)
    }
}