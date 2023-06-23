package com.mutkuensert.gymtools.features.onerm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.pow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.RoundingMode

@HiltViewModel
class OneRmViewModel @Inject constructor() : ViewModel() {
    private val _weightInput = MutableStateFlow("")

    private val _repsInput = MutableStateFlow("")
    val repsInput = _repsInput.asStateFlow()

    private val _epleyRM = MutableStateFlow("")
    val epleyRM = _epleyRM.asStateFlow()

    private val _brzyckiRM = MutableStateFlow("")
    val brzyckiRM = _brzyckiRM.asStateFlow()

    private val _mcglothinRM = MutableStateFlow("")
    val mcglothinRM = _mcglothinRM.asStateFlow()

    private val _lombardiRM = MutableStateFlow("")
    val lombardiRM = _lombardiRM.asStateFlow()

    private val _mayhewRM = MutableStateFlow("")
    val mayhewRM = _mayhewRM.asStateFlow()

    private val _oconnerRM = MutableStateFlow("")
    val oconnerRM = _oconnerRM.asStateFlow()

    private val _wathenRM = MutableStateFlow("")
    val wathenRM = _wathenRM.asStateFlow()

    fun replaceCommaWithDotAndAllowOnlyOneDot(text: String) {
        _weightInput.value = text.replace(",", ".")
        val newText = _weightInput.value
        if (newText.count { it == '.' } > 1 && newText.endsWith(".")) {
            _weightInput.value =
                text.substring(0, text.length - 1)
        }
        if (newText.startsWith(".")) _weightInput.value = ""
    }

    fun dontAllowDotOrComma(text: String) {
        if (text.endsWith(".") || text.endsWith(",")) {
            _repsInput.value =
                text.substring(0, text.length - 1)
        } else {
            _repsInput.value = text
        }
    }

    fun calculateOneRmValues(weight: Double, reps: Int) {
        _epleyRM.value =
            (weight * (1.0 + reps.toDouble() / 30)).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .toString()
        _brzyckiRM.value = (weight * (36 / (37 - reps).toDouble())).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP).toString()
        _mcglothinRM.value = (100 * weight / (101.3 - 2.67123 * reps)).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP).toString()
        _lombardiRM.value =
            (weight * reps.toDouble().pow(0.10)).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .toString()
        _mayhewRM.value = (100 * weight / (52.2 + 41.9 * 2.71828.pow(-0.055 * reps))).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP).toString()
        _oconnerRM.value =
            (weight * (1 + reps.toDouble() / 40)).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .toString()
        _wathenRM.value = (100 * weight / (48.8 + 53.8 * 2.71828.pow(-0.075 * reps))).toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP).toString()
    }
}
