package com.mutkuensert.fitnesspursuit.ui.warmupsetsscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class WarmupSetsViewModel @Inject constructor(): ViewModel() {
    val set2Kg = MutableLiveData<String>()
    val set3Kg = MutableLiveData<String>()
    val set4Kg = MutableLiveData<String>()
    val set5Kg = MutableLiveData<String>()

    fun calculateWarmupSetsWeights(workoutWeight: Double){
        set2Kg.value = (workoutWeight * 0.55).roundToInt().toString()
        set3Kg.value = (workoutWeight * 0.70).roundToInt().toString()
        set4Kg.value = (workoutWeight * 0.80).roundToInt().toString()
        set5Kg.value = (workoutWeight * 0.9).roundToInt().toString()
    }
}