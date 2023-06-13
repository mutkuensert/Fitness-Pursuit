package com.mutkuensert.fitnesspursuit.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mutkuensert.fitnesspursuit.features.bodymeasurements.detail.BodyMeasurementDetailsScreen
import com.mutkuensert.fitnesspursuit.features.bodymeasurements.measurements.BodyMeasurementsScreen
import com.mutkuensert.fitnesspursuit.features.onerm.OneRm
import com.mutkuensert.fitnesspursuit.features.warmupsets.WarmupSets

const val ATHLETE_ID = "id"

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = WARMUP_SCREEN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(WARMUP_SCREEN) { WarmupSets() }

        composable(ONE_RM_SCREEN) { OneRm() }

        navigation(startDestination = BODY_MEASUREMENTS_SCREEN, route = MEASUREMENT) {
            composable(BODY_MEASUREMENTS_SCREEN) {
                BodyMeasurementsScreen({ id: Int? ->
                    val route =
                        BODY_MEASUREMENT_DETAILS_SCREEN.addQueriesIfNotNull(ATHLETE_ID to id?.toString())
                    navController.navigate(route)
                })
            }

            composable(
                route = ROUTE_BODY_MEASUREMENT_DETAILS,
                arguments = listOf(
                    navArgument(ATHLETE_ID) {
                        nullable = true
                    }
                )
            ) {
                BodyMeasurementDetailsScreen(navigateToBodyMeasurements = navController::navigateUp)
            }
        }
    }
}

private fun String.addQueriesIfNotNull(vararg keyValuePair: Pair<String, String?>): String {
    val path = Uri.Builder().path(this)
    keyValuePair.forEach {
        if (it.second != null) {
            path.appendQueryParameter(it.first, it.second)
        }
    }
    return path.toString()
}
