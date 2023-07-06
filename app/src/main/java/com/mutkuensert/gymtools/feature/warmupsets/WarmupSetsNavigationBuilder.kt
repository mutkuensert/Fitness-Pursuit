package com.mutkuensert.gymtools.feature.warmupsets

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mutkuensert.gymtools.navigation.FeatureNavigationBuilder
import javax.inject.Inject

const val ROUTE_WARMUP_SETS: String = "warmup_sets"

class WarmupSetsNavigationBuilder @Inject constructor() : FeatureNavigationBuilder {
    override fun build(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route = ROUTE_WARMUP_SETS) { WarmupSets() }
    }
}
