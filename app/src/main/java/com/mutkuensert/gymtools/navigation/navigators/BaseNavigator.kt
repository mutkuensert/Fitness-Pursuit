package com.mutkuensert.gymtools.navigation.navigators

import androidx.navigation.NavController

interface BaseNavigator {
    var navController: NavController?

    fun navigateHome()
    fun navigateUp(popUpTo: String? = null, isInclusive: Boolean = false)
    fun navigateBack()
}