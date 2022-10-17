package com.mutkuensert.fitnesspursuit.ui.warmupsetsscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

const val TAG = "WarmupSetsViewModel"
@HiltViewModel
class WarmupSetsViewModel @Inject constructor(): ViewModel() {
    val weightInput = MutableLiveData<String>("")

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

    fun replaceCommaWithDotAndAllowOnlyOneDot(text: String){
        weightInput.value = text.replace(",",".")
        val newText = weightInput.value
        if(newText!!.count{ it.equals('.') } > 1 && newText.endsWith(".")) weightInput.value = text.substring(0, text.length-1)
        if(newText.startsWith(".")) weightInput.value = ""
    }
}