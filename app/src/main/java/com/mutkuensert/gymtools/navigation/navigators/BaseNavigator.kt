package com.mutkuensert.gymtools.navigation.navigators

import androidx.navigation.NavController

interface BaseNavigator {
    var navController: NavController?

    fun navigateToHome()
    fun navigateUp(popUpTo: String? = null, isInclusive: Boolean = false)
    fun navigateBack()
    fun navigateToTab(route: String)
}
