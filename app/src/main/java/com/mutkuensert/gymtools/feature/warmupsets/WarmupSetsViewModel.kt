package com.mutkuensert.gymtools.feature.warmupsets

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class WarmupSetsViewModel @Inject constructor() : ViewModel() {
    val weightInput = MutableStateFlow("")
    val set2 = MutableStateFlow("")
    val set3 = MutableStateFlow("")
    val set4 = MutableStateFlow("")
    val set5 = MutableStateFlow("")

    fun calculateWarmupSetsWeights(workoutWeight: Double) {
        set2.value = (workoutWeight * 0.55).roundToInt().toString()
        set3.value = (workoutWeight * 0.70).roundToInt().toString()
        set4.value = (workoutWeight * 0.80).roundToInt().toString()
        set5.value = (workoutWeight * 0.9).roundToInt().toString()
    }

    fun replaceCommaWithDotAndAllowOnlyOneDot(text: String) {
        weightInput.value = text.replace(",", ".")
        val newText = weightInput.value
        if (newText.count { it.equals('.') } > 1 && newText.endsWith(".")) {
            weightInput.value =
                text.substring(0, text.length - 1)
        }
        if (newText.startsWith(".")) weightInput.value = ""
    }
}
