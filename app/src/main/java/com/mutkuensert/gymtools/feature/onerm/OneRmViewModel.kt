package com.mutkuensert.gymtools.feature.onerm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class OneRmViewModel @Inject constructor() : ViewModel() {
    private val _repsInput = MutableStateFlow("")
    val repsInput = _repsInput.asStateFlow()

    private val _epleyRM = MutableStateFlow(0.0)
    private val _brzyckiRM = MutableStateFlow(0.0)
    private val _mcglothinRM = MutableStateFlow(0.0)
    private val _lombardiRM = MutableStateFlow(0.0)
    private val _mayhewRM = MutableStateFlow(0.0)
    private val _oconnerRM = MutableStateFlow(0.0)
    private val _wathenRM = MutableStateFlow(0.0)
    private val _averageRm = MutableStateFlow(0.0)
    val averageRm = _averageRm.asStateFlow()

    fun dontAllowDotOrComma(text: String) {
        if (text.endsWith(".") || text.endsWith(",")) {
            _repsInput.value =
                text.substring(0, text.length - 1)
        } else {
            _repsInput.value = text
        }
    }

    fun calculateOneRmValues(weight: Double, reps: Int) {
        _epleyRM.value = weight * (1.0 + reps.toDouble() / 30)

        _brzyckiRM.value = weight * (36 / (37 - reps).toDouble())

        _mcglothinRM.value = 100 * weight / (101.3 - 2.67123 * reps)

        _lombardiRM.value = weight * reps.toDouble().pow(0.10)

        _mayhewRM.value = 100 * weight / (52.2 + 41.9 * 2.71828.pow(-0.055 * reps))

        _oconnerRM.value = weight * (1 + reps.toDouble() / 40)

        _wathenRM.value = 100 * weight / (48.8 + 53.8 * 2.71828.pow(-0.075 * reps))

        _averageRm.value = listOf(
            _epleyRM.value,
            _brzyckiRM.value,
            _mcglothinRM.value,
            _lombardiRM.value,
            _mayhewRM.value,
            _oconnerRM.value,
            _wathenRM.value
        ).average().withDecimalDigits(1)
    }
}

private fun Double.withDecimalDigits(number: Int = 1): Double {
    var diesisForDecimals = ""

    repeat(number) {
        diesisForDecimals += "#"
    }

    val symbols = DecimalFormatSymbols().apply { decimalSeparator = '.' }
    val df = DecimalFormat("#.$diesisForDecimals", symbols)
    df.roundingMode = RoundingMode.HALF_EVEN
    return df.format(this).toDouble()
}
