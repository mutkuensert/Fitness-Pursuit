package com.mutkuensert.gymtools.feature.base.navigation

import android.util.Log
import androidx.navigation.NavController
import com.mutkuensert.gymtools.feature.warmupsets.ROUTE_WARMUP_SETS
import com.mutkuensert.gymtools.navigation.NavigationDispatcher
import com.mutkuensert.gymtools.navigation.NavigationType
import com.mutkuensert.gymtools.navigation.navigators.BaseNavigator
import javax.inject.Inject

class BaseNavigatorImpl @Inject constructor(private val navigationDispatcher: NavigationDispatcher) :
    BaseNavigator {
    override var navController: NavController? = null

    override fun navigateToHome() {
        try {
            navController!!.navigate(ROUTE_WARMUP_SETS) {
                launchSingleTop = true
                popUpTo(ROUTE_WARMUP_SETS) {
                    inclusive = true
                }
            }
        } catch (e: NullPointerException) {
            Log.e(this::class.simpleName, "navController was not set.")
        }
    }

    override fun navigateUp(popUpTo: String?, isInclusive: Boolean) {
        navigationDispatcher.navigate(NavigationType.Up(popUpTo))
    }

    override fun navigateBack() {
        navigationDispatcher.navigate(NavigationType.Back())
    }

    override fun navigateToTab(route: String) {
        navigationDispatcher.navigate(NavigationType.ToTab(route))
    }
}
