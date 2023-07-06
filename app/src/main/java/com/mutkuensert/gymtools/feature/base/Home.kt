package com.mutkuensert.gymtools.feature.base

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mutkuensert.gymtools.core.getStringRes
import com.mutkuensert.gymtools.feature.warmupsets.ROUTE_WARMUP_SETS
import com.mutkuensert.gymtools.navigation.NavigationBuilder
import com.mutkuensert.gymtools.resources.TextResKeys
import com.mutkuensert.gymtools.ui.theme.AppColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home(navigationBuilder: NavigationBuilder) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val viewModel: HomeViewModel = hiltViewModel()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MyTopAppBar(scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            AppDrawer(
                onCloseDrawer = { scope.launch { scaffoldState.drawerState.close() } },
                navigateToWarmupSets = viewModel::navigateToWarmupSets,
                navigateToOneRm = viewModel::navigateToOneRm,
                navigateToBodyMeasurements = viewModel::navigateToBodyMeasurements
            )
        }
    ) { padding ->

        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = ROUTE_WARMUP_SETS,
            builder = navigationBuilder::buildNavGraph
        )
    }

    navigationBuilder.ObserveNavigation(navController = navController)
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
