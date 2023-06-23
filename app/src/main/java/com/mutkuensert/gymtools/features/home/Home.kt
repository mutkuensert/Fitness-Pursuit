package com.mutkuensert.gymtools.features.home

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
import com.mutkuensert.gymtools.core.getStringRes
import com.mutkuensert.gymtools.navigation.MyNavHost
import com.mutkuensert.gymtools.resources.TextResKeys
import com.mutkuensert.gymtools.ui.theme.AppColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MyTopAppBar(scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            AppDrawer(
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
private fun MyTopAppBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = getStringRes(TextResKeys.APP_NAME)) },
        navigationIcon = {
            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = getStringRes(TextResKeys.APP_BAR_MENU_BUTTON_DESCRIPTION)
                )
            }
        },
        backgroundColor = AppColors.metalGray,
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    Home()
}
