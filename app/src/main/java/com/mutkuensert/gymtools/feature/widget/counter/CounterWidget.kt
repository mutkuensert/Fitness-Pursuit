package com.mutkuensert.gymtools.feature.widget.counter

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.mutkuensert.gymtools.R
import com.mutkuensert.gymtools.ui.theme.AppColors

class CounterWidget : GlanceAppWidget() {
    override var stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    companion object {
        val PREFS_KEY_COUNT = intPreferencesKey("prefs_key_count")
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Content()
        }
    }
}

@Composable
private fun Content() {
    val prefs = currentState<Preferences>()
    val count = prefs[CounterWidget.PREFS_KEY_COUNT] ?: 0

    Row(
        modifier = GlanceModifier
            .padding(horizontal = 10.dp)
            .background(AppColors.metalGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            provider = ImageProvider(resId = R.drawable.minus),
            contentDescription = null,
            modifier = GlanceModifier
                .clickable(onClick = actionRunCallback<DecrementAction>())
                .size(40.dp),
            colorFilter = ColorFilter.tint(ColorProvider(Color.White))
        )

        Spacer(GlanceModifier.width(5.dp))

        Text(
            text = "$count",
            style = TextStyle(color = ColorProvider(Color.White), fontSize = 20.sp)
        )

        Spacer(GlanceModifier.width(5.dp))

        Image(
            provider = ImageProvider(resId = R.drawable.plus),
            contentDescription = null,
            modifier = GlanceModifier
                .clickable(onClick = actionRunCallback<IncrementAction>())
                .size(40.dp),
            colorFilter = ColorFilter.tint(ColorProvider(Color.White))
        )
    }
}
