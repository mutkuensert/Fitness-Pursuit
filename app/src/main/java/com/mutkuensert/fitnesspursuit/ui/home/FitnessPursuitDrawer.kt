package com.mutkuensert.fitnesspursuit.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mutkuensert.fitnesspursuit.R
import com.mutkuensert.fitnesspursuit.ui.components.ShadowedButton
import com.mutkuensert.fitnesspursuit.ui.components.ShadowedCard
import com.mutkuensert.fitnesspursuit.util.ONERMSCREEN
import com.mutkuensert.fitnesspursuit.util.WARMUPSETSSCREEN

@Composable
fun FitnessPursuitDrawer(onCloseDrawer: () -> Unit, navController: NavHostController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 30.dp, end = 20.dp)
    ) {
        Spacer(Modifier.height(25.dp))

        ShadowedCard {
            IconButton(onClick = onCloseDrawer) {
                Icon(Icons.Filled.ArrowBack, context.getString(R.string.close_navigation_menu))
            }
        }

        Spacer(Modifier.height(65.dp))

        ShadowedButton(onClick = {
            navController.navigate(WARMUPSETSSCREEN)
            onCloseDrawer()
        }) {
            Text(text = "Warmup Sets")
        }

        Spacer(Modifier.height(25.dp))

        ShadowedButton(onClick = {
            navController.navigate(ONERMSCREEN)
            onCloseDrawer()
        }) {
            Text(text = "One RM")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDrawer() {
    FitnessPursuitDrawer(onCloseDrawer = {}, navController = rememberNavController())
}
