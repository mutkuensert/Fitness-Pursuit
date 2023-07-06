package com.mutkuensert.gymtools.feature.bodymeasurements.navigation

import com.mutkuensert.gymtools.navigation.NavigationDispatcher
import com.mutkuensert.gymtools.navigation.NavigationType
import com.mutkuensert.gymtools.navigation.navigators.BodyMeasurementsNavigator
import javax.inject.Inject

class BodyMeasurementsNavigatorImpl @Inject constructor(
    private val navigationDispatcher: NavigationDispatcher
) : BodyMeasurementsNavigator {

    override fun navigateToDetails(entityId: Int?) {
        navigationDispatcher.navigate(
            NavigationType.ToRoute(
                route = ROUTE_BODY_MEASUREMENT_DETAILS,
                args = entityId?.let { listOf(ENTITY_ID to entityId) } ?: emptyList()
            )
        )
    }

    override fun navigateToMeasurements() {
        navigationDispatcher.navigate(NavigationType.ToRoute(route = ROUTE_BODY_MEASUREMENTS))
    }
}