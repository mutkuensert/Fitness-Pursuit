package com.mutkuensert.gymtools.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mutkuensert.gymtools.navigation.navigators.BaseNavigator
import javax.inject.Inject

class NavigationBuilder @Inject constructor(
    private val navigationDispatcher: NavigationDispatcher,
    private val builders: Set<@JvmSuppressWildcards FeatureNavigationBuilder>,
    private val baseNavigator: BaseNavigator,
) {
    fun buildNavGraph(navGraphBuilder: NavGraphBuilder) {
        builders.forEach {
            it.build(navGraphBuilder)
        }
    }

    @Composable
    fun ObserveNavigation(navController: NavHostController) {
        baseNavigator.navController = navController

        LaunchedEffect(navController) {
            navigationDispatcher.commands.collect {
                when (it) {
                    is NavigationType.Back -> navController.popBackStack()
                    is NavigationType.Up -> navigateUp(navController, it)
                    is NavigationType.ToRoute -> navController.navigate(it.getRoute())
                }
            }
        }
    }

    private fun navigateUp(
        navController: NavHostController,
        command: NavigationType.Up
    ) {
        if (command.route != null) {
            val startDestination = navController.graph.startDestinationRoute
            val isPopped = navController.popBackStack(command.route, command.isInclusive)

            if (!isPopped && startDestination != null) {
                Log.e(
                    this::class.simpleName,
                    "Back stack could not be popped. The user has been navigated to start destination route."
                )
                navController.navigate(startDestination)
            }
        } else {
            navController.navigateUp()
        }
    }
}