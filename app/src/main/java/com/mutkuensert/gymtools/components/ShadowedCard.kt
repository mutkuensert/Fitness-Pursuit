package com.mutkuensert.gymtools.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShadowedCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = MaterialTheme.colors.surface,
    innerPadding: Dp = 5.dp,
    elevation: Dp = 5.dp,
    animateContentSize: Boolean = false,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .then(
                Modifier
                    .shadow(elevation = elevation, shape = shape)
                    .background(color = backgroundColor, shape = shape)
            )
            .then(if (animateContentSize) Modifier.animateContentSize() else Modifier),
        shape = shape
    ) {
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = backgroundColor, shape = shape)
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewShadowedCard() {
    Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center) {
        ShadowedCard {
            Text(text = "This is a text.")
        }
    }
}
