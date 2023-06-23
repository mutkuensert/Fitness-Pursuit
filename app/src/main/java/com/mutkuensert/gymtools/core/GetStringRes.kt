package com.mutkuensert.gymtools.core

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getStringRes(key: String, args: Array<Any>? = null): String {
    val context = LocalContext.current

    return getString(context = context, key = key, args = args)
}

fun getStringRes(context: Context, key: String, args: Array<Any>? = null): String {
    return getString(context = context, key = key, args = args)
}

private fun getString(context: Context, key: String, args: Array<Any>? = null): String {
    val resources = context.resources
    val resourceId =
        resources.getIdentifier(key, "string", context.packageName)

    return if (args == null) {
        resources.getString(resourceId)
    } else {
        resources.getString(resourceId, *args)
    }
}
