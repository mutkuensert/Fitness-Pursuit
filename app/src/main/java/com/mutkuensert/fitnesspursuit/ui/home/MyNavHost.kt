package com.mutkuensert.fitnesspursuit.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mutkuensert.fitnesspursuit.ui.bodysizesscreen.BodySizesScreen
import com.mutkuensert.fitnesspursuit.ui.onermscreen.OneRmScreen
import com.mutkuensert.fitnesspursuit.ui.warmupsetsscreen.WarmupSetsScreen
import com.mutkuensert.fitnesspursuit.util.BODYSIZESSCREEN
import com.mutkuensert.fitnesspursuit.util.ONERMSCREEN
import com.mutkuensert.fitnesspursuit.util.WARMUPSETSSCREEN

@Composable
fun MyNavHost(
    context: Context,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = WARMUPSETSSCREEN){
    NavHost(modifier = modifier, navController = navController, startDestination = startDestination){
        composable(WARMUPSETSSCREEN){ WarmupSetsScreen(context) }
        composable(ONERMSCREEN){ OneRmScreen(context) }
        composable(BODYSIZESSCREEN){ BodySizesScreen(context) }
    }
}