package com.mutkuensert.gymtools.features.onerm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.math.RoundingMode
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class OneRmViewModel @Inject constructor() : ViewModel() {
    val weightInput = MutableStateFlow("")
    val repsInput = MutableStateFlow("")
    val epleyRM = MutableStateFlow("")
    val brzyckiRM = MutableStateFlow("")
    val mcglothinRM = MutableStateFlow("")
    val lombardiRM = MutableStateFlow("")
    val mayhewRM = MutableStateFlow("")
    val oconnerRM = MutableStateFlow("")
    val wathenRM = MutableStateFlow("")

    fun replaceCommaWithDotAndAllowOnlyOneDot(text: String) {
        weightInput.value = text.replace(",", ".")
        val newText = weightInput.value
        if (newText.count { it.equals('.') } > 1 && newText.endsWith(".")) {
            weightInput.value =
                text.substring(0, text.length - 1)
        }
        if (newText.startsWith(".")) weightInput.value = ""
    }

    fun dontAllowDotOrComma(text: String) {
        if (text.endsWith(".") || text.endsWith(",")) {
            repsInput.value =
                text.substring(0, text.length - 1)
        } else {
            repsInput.value = text
        }
    }

    fun calculateOneRmValues(weight: Double, reps: Int) {
        epleyRM.value =
            (weight * (1.0 + reps.toDouble() / 30)).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .toString()
        brzyckiRM.value = (weight * (36 / (37 - reps).toDouble())).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP).toString()
        mcglothinRM.value = (100 * weight / (101.3 - 2.67123 * reps)).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP).toString()
        lombardiRM.value =
            (weight * reps.toDouble().pow(0.10)).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .toString()
        mayhewRM.value = (100 * weight / (52.2 + 41.9 * 2.71828.pow(-0.055 * reps))).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP).toString()
        oconnerRM.value =
            (weight * (1 + reps.toDouble() / 40)).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .toString()
        wathenRM.value = (100 * weight / (48.8 + 53.8 * 2.71828.pow(-0.075 * reps))).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP).toString()
    }
}
