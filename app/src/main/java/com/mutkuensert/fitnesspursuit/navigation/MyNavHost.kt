package com.mutkuensert.fitnesspursuit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mutkuensert.fitnesspursuit.features.bodysizes.bodysizelist.BodySizeListScreen
import com.mutkuensert.fitnesspursuit.features.bodysizes.detail.BodySizeDetailsScreen
import com.mutkuensert.fitnesspursuit.features.onerm.OneRm
import com.mutkuensert.fitnesspursuit.features.warmupsets.WarmupSets

const val ATHLETE_ID = "id"

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

        navigation(startDestination = BODY_SIZE_LIST, route = BODY_SIZES) {
            composable(BODY_SIZE_LIST) {
                BodySizeListScreen({
                    navController.navigate(
                        BODY_SIZE_DETAILS
                    )
                })
            }

            composable(
                route = "$BODY_SIZE_DETAILS?$ATHLETE_ID={$ATHLETE_ID}",
                arguments = listOf(navArgument(ATHLETE_ID) { nullable = true })
            ) {
                BodySizeDetailsScreen({
                    navController.navigate(BODY_SIZE_LIST) {
                        popUpTo(BODY_SIZE_LIST)
                    }
                })
            }
        }
    }
}
