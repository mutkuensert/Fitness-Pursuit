package com.mutkuensert.gymtools.feature.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutkuensert.gymtools.components.ShadowedButton
import com.mutkuensert.gymtools.components.ShadowedCard
import com.mutkuensert.gymtools.core.getStringRes
import com.mutkuensert.gymtools.resources.TextResKeys

private const val VERTICAL_SPACE = 15

@Composable
fun AppDrawer(
    onCloseDrawer: () -> Unit,
    navigateToTab: (route: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.spacedBy(VERTICAL_SPACE.dp)
    ) {
        ShadowedCard {
            IconButton(onClick = onCloseDrawer) {
                Icon(Icons.Filled.ArrowBack, getStringRes(TextResKeys.CLOSE_NAVIGATION_MENU))
            }
        }

        ShadowedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navigateToTab(Tabs.WarmupSets.route)
                onCloseDrawer()
            }
        ) {
            Text(text = getStringRes(Tabs.WarmupSets.titleKey))
        }

        ShadowedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navigateToTab(Tabs.OneRm.route)
                onCloseDrawer()
            }
        ) {
            Text(text = getStringRes(Tabs.OneRm.titleKey))
        }

        ShadowedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navigateToTab(Tabs.BodyMeasurements.route)
                onCloseDrawer()
            }
        ) {
            Text(text = getStringRes(Tabs.BodyMeasurements.titleKey))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewDrawer() {
    AppDrawer(
        onCloseDrawer = {},
        navigateToTab = {}
    )
}
