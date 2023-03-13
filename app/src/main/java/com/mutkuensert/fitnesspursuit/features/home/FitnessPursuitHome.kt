package com.mutkuensert.fitnesspursuit.features.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mutkuensert.fitnesspursuit.core.getStringRes
import com.mutkuensert.fitnesspursuit.navigation.MyNavHost
import com.mutkuensert.fitnesspursuit.resources.TextResKeys
import com.mutkuensert.fitnesspursuit.ui.theme.TextColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FitnessPursuitHome() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MyTopAppBar(scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            FitnessPursuitDrawer(
                { scope.launch { scaffoldState.drawerState.close() } },
                navController
            )
        }
    ) { padding ->
        MyNavHost(
            modifier = Modifier.padding(padding),
            navController = navController
        )
    }
}

@Composable
fun MyTopAppBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = "Fitness Pursuit") },
        navigationIcon = {
            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = getStringRes(TextResKeys.APP_BAR_MENU_BUTTON_DESCRIPTION)
                )
            }
        },
        backgroundColor = TextColors.pennRed,
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun FitnessPursuitHomePreview() {
    FitnessPursuitHome()
}
