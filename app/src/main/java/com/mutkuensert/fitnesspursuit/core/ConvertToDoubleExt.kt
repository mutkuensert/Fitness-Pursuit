package com.mutkuensert.fitnesspursuit.core

fun String.convertToDouble(): Double {
    val newValue: String = this.replace(",", ".")
    var withoutExtraDots = ""
    var indexOfFirstDot: Int? = null
    var index = 0

    for (c in newValue) {
        if (index == 0 && c == '.') return 0.0
        if (indexOfFirstDot == null && c == '.') indexOfFirstDot = index

        withoutExtraDots += if (c == '.' && index != indexOfFirstDot) {
            continue
        } else {
            c
        }
        index += 1
    }

    return withoutExtraDots.toDouble()
}
