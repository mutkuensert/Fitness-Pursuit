package com.mutkuensert.fitnesspursuit.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getString(key: String, args: Array<Any>? = null): String {
    val context = LocalContext.current
    val resources = context.resources
    val resourceId =
        resources.getIdentifier(key, "string", context.packageName)

    return if (args == null) {
        resources.getString(resourceId)
    } else {
        resources.getString(resourceId, *args)
    }
}