package com.mutkuensert.fitnesspursuit.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun ShadowedCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .shadow(elevation = 5.dp)
            .animateContentSize(),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            content()
        }
    }
}
