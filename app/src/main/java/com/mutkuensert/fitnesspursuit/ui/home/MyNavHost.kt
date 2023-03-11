package com.mutkuensert.fitnesspursuit.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mutkuensert.fitnesspursuit.ui.bodysizes.BodySizes
import com.mutkuensert.fitnesspursuit.ui.onerm.OneRm
import com.mutkuensert.fitnesspursuit.ui.warmupsets.WarmupSets
import com.mutkuensert.fitnesspursuit.util.BODYSIZESSCREEN
import com.mutkuensert.fitnesspursuit.util.ONERMSCREEN
import com.mutkuensert.fitnesspursuit.util.WARMUPSETSSCREEN

@Composable
fun MyNavHost(
    context: Context,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = WARMUPSETSSCREEN
) {
    NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
        composable(WARMUPSETSSCREEN) { WarmupSets(context) }
        composable(ONERMSCREEN) { OneRm(context) }
        composable(BODYSIZESSCREEN) { BodySizes() }
    }
}
