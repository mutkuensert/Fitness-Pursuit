package com.mutkuensert.fitnesspursuit.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShadowedCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 5.dp,
    animateContentSize: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.surface,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .then(
                modifier
                    .wrapContentSize()
                    .shadow(elevation = elevation)
            )
            .run { if (animateContentSize) animateContentSize() else this },
        backgroundColor = backgroundColor
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            content()
        }
    }
}
