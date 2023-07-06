package com.mutkuensert.gymtools.feature.base

import androidx.lifecycle.ViewModel
import com.mutkuensert.gymtools.navigation.navigators.BodyMeasurementsNavigator
import com.mutkuensert.gymtools.navigation.navigators.OneRmNavigator
import com.mutkuensert.gymtools.navigation.navigators.WarmupSetsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val oneRmNavigator: OneRmNavigator,
    private val warmupSetsNavigator: WarmupSetsNavigator,
    private val bodyMeasurementsNavigator: BodyMeasurementsNavigator,
) : ViewModel() {

    fun navigateToOneRm() {
        oneRmNavigator.navigateToOneRm()
    }

    fun navigateToWarmupSets() {
        warmupSetsNavigator.navigateToWarmupSets()
    }

    fun navigateToBodyMeasurements() {
        bodyMeasurementsNavigator.navigateToMeasurements()
    }
}