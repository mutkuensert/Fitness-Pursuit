package com.mutkuensert.gymtools.feature.onerm

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mutkuensert.gymtools.navigation.FeatureNavigationBuilder
import javax.inject.Inject

const val ROUTE_ONE_RM: String = "one_rm"

class OneRmNavigationBuilder @Inject constructor() : FeatureNavigationBuilder {
    override fun build(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route = ROUTE_ONE_RM) { OneRm() }
    }
}
