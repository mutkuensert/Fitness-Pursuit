package com.mutkuensert.gymtools.feature.onerm

import com.mutkuensert.gymtools.navigation.NavigationDispatcher
import com.mutkuensert.gymtools.navigation.NavigationType
import com.mutkuensert.gymtools.navigation.navigators.OneRmNavigator
import javax.inject.Inject

class OneRmNavigatorImpl @Inject constructor(private val navigationDispatcher: NavigationDispatcher) :
    OneRmNavigator {

    override fun navigateToOneRm() {
        navigationDispatcher.navigate(NavigationType.ToRoute(ROUTE_ONE_RM))
    }
}
