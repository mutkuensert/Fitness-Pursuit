package com.mutkuensert.gymtools.navigation

import android.net.Uri

fun String.getWithQueriesWithoutNullValuePairs(keyValuePairs: List<Pair<String, Any?>>): String {
    return addQueriesToRouteWithoutNullValuePairs(this, keyValuePairs)
}

fun String.getWithQueriesWithoutNullValuePairs(vararg keyValuePairs: Pair<String, Any?>): String {
    return addQueriesToRouteWithoutNullValuePairs(this, keyValuePairs.toList())
}

private fun addQueriesToRouteWithoutNullValuePairs(
    route: String,
    keyValuePairs: List<Pair<String, Any?>>
): String {
    val path = Uri.Builder().path(route)
    keyValuePairs.forEach {
        if (it.second != null) {
            path.appendQueryParameter(it.first, it.second.toString())
        }
    }
    return path.toString()
}
