package com.mutkuensert.fitnesspursuit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShadowedButton(onClick: () -> Unit, content: @Composable () -> Unit) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.elevation(defaultElevation = 5.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)
    ) {
        content()
    }
}

@Preview
@Composable
fun ShadowedButtonPreview() {
    Surface {
        Column {
            ShadowedButton(onClick = { }) {
                Text(text = "Text")
            }
        }
    }
}
