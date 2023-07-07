package com.mutkuensert.gymtools.feature.warmupsets

import com.mutkuensert.gymtools.navigation.NavigationDispatcher
import com.mutkuensert.gymtools.navigation.NavigationType
import com.mutkuensert.gymtools.navigation.navigators.WarmupSetsNavigator
import javax.inject.Inject

class WarmupSetsNavigatorImpl @Inject constructor(private val navigationDispatcher: NavigationDispatcher) :
    WarmupSetsNavigator {

    override fun navigateToWarmupSets() {
        navigationDispatcher.navigate(NavigationType.ToRoute(ROUTE_WARMUP_SETS))
    }
}
