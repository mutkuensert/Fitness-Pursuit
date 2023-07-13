package com.mutkuensert.gymtools

import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.google.android.glance.tools.viewer.GlanceSnapshot
import com.google.android.glance.tools.viewer.GlanceViewerActivity
import com.mutkuensert.gymtools.feature.widget.counter.CounterWidget
import com.mutkuensert.gymtools.feature.widget.counter.CounterWidgetReceiver

/**
 * A nullability error exists in this version.
 * Update when a new version is released.
 * https://github.com/google/glance-experimental-tools/issues/11
 */
@OptIn(ExperimentalGlanceRemoteViewsApi::class)
class WidgetViewerActivity : GlanceViewerActivity() {
    override suspend fun getGlanceSnapshot(receiver: Class<out GlanceAppWidgetReceiver>): GlanceSnapshot {
        return when (receiver) {
            CounterWidgetReceiver::class.java -> GlanceSnapshot(
                instance = CounterWidget(),
                state = mutablePreferencesOf(CounterWidget.PREFS_KEY_COUNT to 0)
            )

            else -> throw IllegalArgumentException()
        }
    }

    override fun getProviders() = listOf(CounterWidgetReceiver::class.java)
}