package com.mutkuensert.fitnesspursuit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mutkuensert.fitnesspursuit.features.bodysizes.BodySizes
import com.mutkuensert.fitnesspursuit.features.onerm.OneRm
import com.mutkuensert.fitnesspursuit.features.warmupsets.WarmupSets

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = WARMUP_SETS
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(WARMUP_SETS) { WarmupSets() }
        composable(ONE_RM) { OneRm() }
        composable(BODY_SIZES) { BodySizes() }
    }
}
