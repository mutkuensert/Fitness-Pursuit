package com.mutkuensert.gymtools.feature.widget.counter

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition

class IncrementAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) {
            it.toMutablePreferences().apply {
                val count = this[CounterWidget.PREFS_KEY_COUNT] ?: 0
                this[CounterWidget.PREFS_KEY_COUNT] = count + 1
            }
        }

        CounterWidget().update(context, glanceId)
    }
}