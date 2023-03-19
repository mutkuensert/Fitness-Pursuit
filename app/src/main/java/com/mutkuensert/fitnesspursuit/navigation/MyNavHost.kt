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
    startDestination: String = WARMUP_SETS
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(WARMUP_SETS) { WarmupSets() }

        composable(ONE_RM) { OneRm() }

        navigation(startDestination = BODY_MEASUREMENTS, route = MEASUREMENT) {
            composable(BODY_MEASUREMENTS) {
                BodyMeasurementsScreen({ id: Int? ->
                    navController.navigate(
                        Uri.Builder()
                            .path(BODY_MEASUREMENT_DETAILS)
                            .apply { id?.let { appendQueryParameter(ATHLETE_ID, it.toString()) } }
                            .toString()
                    )
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
                BodyMeasurementDetailsScreen({
                    navController.navigate(BODY_MEASUREMENTS) {
                        popUpTo(BODY_MEASUREMENTS)
                    }
                })
            }
        }
    }
}
