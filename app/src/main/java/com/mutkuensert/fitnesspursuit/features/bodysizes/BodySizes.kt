package com.mutkuensert.fitnesspursuit.features.bodysizes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutkuensert.fitnesspursuit.R
import com.mutkuensert.fitnesspursuit.core.getStringRes
import com.mutkuensert.fitnesspursuit.resources.TextResKeys
import com.mutkuensert.fitnesspursuit.ui.theme.TextColors
import com.mutkuensert.fitnesspursuit.ui.theme.appTypography
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

private const val VERTICAL_SPACE = 5

@Composable
fun BodySizes() {
    val context = LocalContext.current
    val (leftBicep, onLeftBicepChange) = remember { mutableStateOf("") }
    val (rightBicep, onRightBicepChange) = remember { mutableStateOf("") }
    val (leftForearm, onLeftForearmChange) = remember { mutableStateOf("") }
    val (rightForearm, onRightForearmChange) = remember { mutableStateOf("") }
    val (leftCalf, onLeftCalfChange) = remember { mutableStateOf("") }
    val (rightCalf, onRightCalfChange) = remember { mutableStateOf("") }
    val (leftThigh, onLeftThighChange) = remember { mutableStateOf("") }
    val (rightThigh, onRightThighChange) = remember { mutableStateOf("") }
    val (chest, onChestChange) = remember { mutableStateOf("") }
    val (hips, onHipsChange) = remember { mutableStateOf("") }
    val (neck, onNeckChange) = remember { mutableStateOf("") }
    val (shoulders, onShouldersChange) = remember { mutableStateOf("") }
    val (waist, onWaistChange) = remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf(LocalDateTime.now()) }

    val timePicker =
        TimePickerDialog(
            context,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                dateTime = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.of(hourOfDay, minute))
            },
            dateTime.hour,
            dateTime.minute,
            true
        )

    val datePicker = DatePickerDialog(
        context,
        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val time = dateTime.toLocalTime()
            dateTime = LocalDateTime.of(
                LocalDate.of(year, month, dayOfMonth),
                LocalTime.of(time.hour, time.minute)
            )
            timePicker.show()
        },
        dateTime.year,
        dateTime.monthValue,
        dateTime.dayOfMonth
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(VERTICAL_SPACE.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(VERTICAL_SPACE.dp))

        LeftRightMeasurementField(
            title = getStringRes(TextResKeys.BICEP),
            left = leftBicep,
            onLeftChange = onLeftBicepChange,
            right = rightBicep,
            onRightChange = onRightBicepChange
        )

        LeftRightMeasurementField(
            title = getStringRes(TextResKeys.FOREARM),
            left = leftForearm,
            onLeftChange = onLeftForearmChange,
            right = rightForearm,
            onRightChange = onRightForearmChange
        )

        LeftRightMeasurementField(
            title = getStringRes(TextResKeys.CALF),
            left = leftCalf,
            onLeftChange = onLeftCalfChange,
            right = rightCalf,
            onRightChange = onRightCalfChange
        )

        LeftRightMeasurementField(
            title = getStringRes(TextResKeys.THIGH),
            left = leftThigh,
            onLeftChange = onLeftThighChange,
            right = rightThigh,
            onRightChange = onRightThighChange
        )

        Column(
            modifier = Modifier.width(240.dp),
            verticalArrangement = Arrangement.spacedBy(VERTICAL_SPACE.dp)
        ) {
            MeasurementField(
                value = chest,
                onValueChange = onChestChange,
                label = getStringRes(TextResKeys.CHEST)
            )

            MeasurementField(
                value = hips,
                onValueChange = onHipsChange,
                label = getStringRes(TextResKeys.HIPS)
            )

            MeasurementField(
                value = neck,
                onValueChange = onNeckChange,
                label = getStringRes(TextResKeys.SHOULDERS)
            )

            MeasurementField(
                value = shoulders,
                onValueChange = onShouldersChange,
                label = getStringRes(TextResKeys.SHOULDERS)
            )

            MeasurementField(
                value = waist,
                onValueChange = onWaistChange,
                label = getStringRes(TextResKeys.WAIST)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.clickable { datePicker.show() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            with(dateTime) {
                Text(
                    text = "$dayOfMonth.$monthValue.$year $hour:$minute ",
                    style = MaterialTheme.appTypography.h6,
                    color = TextColors.viridianGreen
                )
            }

            Icon(
                modifier = Modifier.requiredHeight(20.dp),
                painter = painterResource(R.drawable.open),
                tint = TextColors.viridianGreen,
                contentDescription = getStringRes(TextResKeys.SET_CUSTOM_DATE_TIME)
            )
        }

        Text(
            modifier = Modifier.clickable { },
            text = getStringRes(TextResKeys.SAVE),
            style = MaterialTheme.appTypography.h6,
            color = TextColors.viridianGreen
        )

        Spacer(modifier = Modifier.height(VERTICAL_SPACE.dp))
    }
}

@Composable
fun LeftRightMeasurementField(
    modifier: Modifier = Modifier,
    title: String,
    left: String,
    onLeftChange: (String) -> Unit,
    right: String,
    onRightChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            MeasurementField(
                modifier = Modifier
                    .requiredWidth(120.dp),
                value = left,
                onValueChange = onLeftChange,
                placeholderResId = R.string.left,
                label = title
            )

            MeasurementField(
                modifier = Modifier
                    .requiredWidth(120.dp),
                value = right,
                onValueChange = onRightChange,
                placeholderResId = R.string.right,
                label = title
            )
        }
    }
}

@Composable
fun MeasurementField(
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholderResId: Int? = null,
    value: String,
    onValueChange: (String) -> Unit
) {
    val context = LocalContext.current
    val placeholderText = placeholderResId?.let { context.getString(it) }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        placeholder = {
            if (placeholderText != null) {
                Text(text = placeholderText)
            }
        },
        label = {
            if (label != null) {
                Text(text = label)
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBodySizes() {
    BodySizes()
}
