package com.mutkuensert.fitnesspursuit.ui.home

import android.content.Context
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

@Composable
fun FitnessPursuitHome(context: Context){
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MyTopAppBar(context = context, scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            FitnessPursuitDrawer(
                {scope.launch { scaffoldState.drawerState.close() }},
                navController
            )
        }
    ) { padding ->
        MyNavHost(
            context = context,
            modifier = Modifier.padding(padding),
            navController = navController)
    }
}

@Composable
fun MyTopAppBar(context: Context, scope: CoroutineScope, scaffoldState: ScaffoldState){
    TopAppBar(
        title = { Text(text = "Fitness Pursuit")},
        navigationIcon = {
            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                Icon(Icons.Filled.Menu, contentDescription = context.getString(com.mutkuensert.fitnesspursuit.R.string.app_bar_menu_button_description))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FitnessPursuitHomePreview(){
    FitnessPursuitHome(context = LocalContext.current)
}