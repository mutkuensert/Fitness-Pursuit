package com.mutkuensert.gymtools.features.home

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mutkuensert.gymtools.components.ShadowedButton
import com.mutkuensert.gymtools.components.ShadowedCard
import com.mutkuensert.gymtools.core.getStringRes
import com.mutkuensert.gymtools.navigation.MEASUREMENT
import com.mutkuensert.gymtools.navigation.ONE_RM_SCREEN
import com.mutkuensert.gymtools.navigation.WARMUP_SCREEN
import com.mutkuensert.gymtools.resources.TextResKeys

private const val VERTICAL_SPACE = 15

@Composable
fun AppDrawer(onCloseDrawer: () -> Unit, navController: NavHostController) {
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
                navController.navigate(WARMUP_SCREEN)
                onCloseDrawer()
            }
        ) {
            Text(text = getStringRes(TextResKeys.WARMUP_SETS))
        }

        ShadowedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(ONE_RM_SCREEN)
                onCloseDrawer()
            }
        ) {
            Text(text = getStringRes(TextResKeys.ONE_RM))
        }

        ShadowedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(MEASUREMENT)
                onCloseDrawer()
            }
        ) {
            Text(text = getStringRes(TextResKeys.BODY_SIZES))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewDrawer() {
    AppDrawer(onCloseDrawer = {}, navController = rememberNavController())
}
