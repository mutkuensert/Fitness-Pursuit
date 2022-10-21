package com.mutkuensert.fitnesspursuit.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mutkuensert.fitnesspursuit.ui.MyNavHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview(showSystemUi = true)
@Composable
fun FitnessPursuitHome(){
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MyTopAppBar(scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            FitnessPursuitDrawer(
                {scope.launch { scaffoldState.drawerState.close() }},
                navController
            )
        }
    ) { padding ->
        MyNavHost(modifier = Modifier.padding(padding), navController = navController)
    }
}

@Composable
fun MyTopAppBar(scope: CoroutineScope, scaffoldState: ScaffoldState){
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = "Fitness Pursuit")},
        navigationIcon = {
            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                Icon(Icons.Filled.Menu, contentDescription = context.getString(com.mutkuensert.fitnesspursuit.R.string.app_bar_menu_button_description))
            }
        }
    )
}