package com.mutkuensert.gymtools.feature.base

import androidx.lifecycle.ViewModel
import com.mutkuensert.gymtools.navigation.navigators.BaseNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val baseNavigator: BaseNavigator
) : ViewModel() {

    fun navigateToTab(route: String) {
        baseNavigator.navigateToTab(route)
    }
}
