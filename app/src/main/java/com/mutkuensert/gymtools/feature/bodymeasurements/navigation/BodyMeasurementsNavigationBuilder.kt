package com.mutkuensert.gymtools.feature.bodymeasurements.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mutkuensert.gymtools.feature.bodymeasurements.detail.BodyMeasurementDetailsScreen
import com.mutkuensert.gymtools.feature.bodymeasurements.measurements.BodyMeasurementsScreen
import com.mutkuensert.gymtools.navigation.FeatureNavigationBuilder
import javax.inject.Inject

const val ENTITY_ID = "id"
const val ROUTE_MEASUREMENT: String = "measurement"
const val ROUTE_BODY_MEASUREMENTS: String = "body_measurements"
const val ROUTE_BODY_MEASUREMENT_DETAILS = "body_measurement_details?$ENTITY_ID={$ENTITY_ID}"

class BodyMeasurementsNavigationBuilder @Inject constructor() : FeatureNavigationBuilder {
    override fun build(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = ROUTE_BODY_MEASUREMENTS,
            route = ROUTE_MEASUREMENT
        ) {
            composable(route = ROUTE_BODY_MEASUREMENTS) {
                BodyMeasurementsScreen()
            }

            composable(
                route = ROUTE_BODY_MEASUREMENT_DETAILS
            ) {
                BodyMeasurementDetailsScreen()
            }
        }
    }
}
