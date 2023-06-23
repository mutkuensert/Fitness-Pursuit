package com.mutkuensert.gymtools.features.bodymeasurements.detail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mutkuensert.gymtools.R
import com.mutkuensert.gymtools.components.AutoCompleteTextField
import com.mutkuensert.gymtools.core.convertToDouble
import com.mutkuensert.gymtools.core.getStringRes
import com.mutkuensert.gymtools.data.BodyMeasurementDetailsDto
import com.mutkuensert.gymtools.resources.TextResKeys
import com.mutkuensert.gymtools.ui.theme.AppColors
import com.mutkuensert.gymtools.ui.theme.appTypography
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

private const val VERTICAL_SPACE = 5
private const val HORIZONTAL_SPACE = 10
private const val MEASUREMENT_FIELD_WIDTH = 120
private const val OVERALL_WIDTH = MEASUREMENT_FIELD_WIDTH * 2 + HORIZONTAL_SPACE

@Composable
fun BodyMeasurementDetailsScreen(
    navigateToBodyMeasurements: () -> Unit,
    viewModel: BodyMeasurementDetailsViewModel = hiltViewModel()
) {
    val athleteNames = viewModel.athleteNames.collectAsStateWithLifecycle()
    val bodyMeasurementDetails = viewModel.bodyMeasurementDetails.collectAsStateWithLifecycle()

    BodyMeasurementDetails(
        navigateToBodyMeasurements,
        viewModel::saveData,
        athleteNames.value,
        bodyMeasurementDetails.value
    )
}

@Composable
private fun BodyMeasurementDetails(
    navigateToBodySizeList: () -> Unit,
    saveData: (BodyMeasurementDetailsDto) -> Unit,
    athleteNames: List<String>,
    bodyMeasurementDetails: BodyMeasurementDetailsDto?
) {
    val context = LocalContext.current
    val (name, onNameChange) = remember { mutableStateOf("") }
    val (weight, onWeightChange) = remember { mutableStateOf("") }
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

    LaunchedEffect(bodyMeasurementDetails) {
        bodyMeasurementDetails?.let {
            onNameChange(it.athleteName)
            onWeightChange(it.weight?.toString() ?: "")
            onLeftBicepChange(it.leftBicep?.toString() ?: "")
            onRightBicepChange(it.rightBicep?.toString() ?: "")
            onLeftForearmChange(it.leftForearm?.toString() ?: "")
            onRightForearmChange(it.rightForearm?.toString() ?: "")
            onLeftCalfChange(it.leftCalf?.toString() ?: "")
            onRightCalfChange(it.rightCalf?.toString() ?: "")
            onLeftThighChange(it.leftThigh?.toString() ?: "")
            onRightThighChange(it.rightThigh?.toString() ?: "")
            onChestChange(it.chest?.toString() ?: "")
            onHipsChange(it.hips?.toString() ?: "")
            onNeckChange(it.neck?.toString() ?: "")
            onShouldersChange(it.shoulders?.toString() ?: "")
            onWaistChange(it.waist?.toString() ?: "")
            dateTime = it.date
        }
    }

    val timePicker = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            dateTime = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.of(hourOfDay, minute))
        },
        dateTime.hour,
        dateTime.minute,
        true
    )

    val datePicker = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val time = dateTime.toLocalTime()
            dateTime = LocalDateTime.of(
                LocalDate.of(year, month + 1, dayOfMonth),
                LocalTime.of(time.hour, time.minute)
            )
            timePicker.show()
        },
        dateTime.year,
        dateTime.monthValue - 1,
        dateTime.dayOfMonth
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .width(OVERALL_WIDTH.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(VERTICAL_SPACE.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(VERTICAL_SPACE.dp))

            AutoCompleteTextField(
                value = name,
                onValueChange = onNameChange,
                label = getStringRes(TextResKeys.ATHLETE_NAME),
                placeholderText = getStringRes(TextResKeys.ATHLETE_NAME),
                data = athleteNames
            )

            MeasurementTextField(
                value = weight,
                onValueChange = onWeightChange,
                label = getStringRes(TextResKeys.WEIGHT)
            )

            LeftRightMeasurementTextField(
                title = getStringRes(TextResKeys.BICEP),
                left = leftBicep,
                onLeftChange = onLeftBicepChange,
                right = rightBicep,
                onRightChange = onRightBicepChange
            )

            LeftRightMeasurementTextField(
                title = getStringRes(TextResKeys.FOREARM),
                left = leftForearm,
                onLeftChange = onLeftForearmChange,
                right = rightForearm,
                onRightChange = onRightForearmChange
            )

            LeftRightMeasurementTextField(
                title = getStringRes(TextResKeys.CALF),
                left = leftCalf,
                onLeftChange = onLeftCalfChange,
                right = rightCalf,
                onRightChange = onRightCalfChange
            )

            LeftRightMeasurementTextField(
                title = getStringRes(TextResKeys.THIGH),
                left = leftThigh,
                onLeftChange = onLeftThighChange,
                right = rightThigh,
                onRightChange = onRightThighChange
            )

            MeasurementTextField(
                value = chest,
                onValueChange = onChestChange,
                label = getStringRes(TextResKeys.CHEST)
            )

            MeasurementTextField(
                value = hips,
                onValueChange = onHipsChange,
                label = getStringRes(TextResKeys.HIPS)
            )

            MeasurementTextField(
                value = neck,
                onValueChange = onNeckChange,
                label = getStringRes(TextResKeys.NECK)
            )

            MeasurementTextField(
                value = shoulders,
                onValueChange = onShouldersChange,
                label = getStringRes(TextResKeys.SHOULDERS)
            )

            MeasurementTextField(
                value = waist,
                onValueChange = onWaistChange,
                label = getStringRes(TextResKeys.WAIST)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.clickable { datePicker.show() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                with(dateTime) {
                    Text(
                        text = "${toLocalDate()} ${toLocalTime().hour}.${toLocalTime().minute} ",
                        style = MaterialTheme.appTypography.h6,
                        color = AppColors.metalGray
                    )
                }

                Icon(
                    modifier = Modifier.requiredHeight(20.dp),
                    painter = painterResource(R.drawable.open),
                    tint = AppColors.metalGray,
                    contentDescription = getStringRes(TextResKeys.SET_CUSTOM_DATE_TIME)
                )
            }

            Text(
                modifier = Modifier.clickable {
                    saveData(
                        BodyMeasurementDetailsDto(
                            name,
                            dateTime,
                            weight.toDoubleOrNull(),
                            leftBicep.toDoubleOrNull(),
                            rightBicep.toDoubleOrNull(),
                            leftForearm.toDoubleOrNull(),
                            rightForearm.toDoubleOrNull(),
                            leftCalf.toDoubleOrNull(),
                            rightCalf.toDoubleOrNull(),
                            leftThigh.toDoubleOrNull(),
                            rightThigh.toDoubleOrNull(),
                            chest.toDoubleOrNull(),
                            hips.toDoubleOrNull(),
                            neck.toDoubleOrNull(),
                            shoulders.toDoubleOrNull(),
                            waist.toDoubleOrNull()
                        )
                    )

                    navigateToBodySizeList()
                },
                text = getStringRes(TextResKeys.SAVE),
                style = MaterialTheme.appTypography.h6,
                color = AppColors.metalGray
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun LeftRightMeasurementTextField(
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
            horizontalArrangement = Arrangement.spacedBy(HORIZONTAL_SPACE.dp)
        ) {
            MeasurementTextField(
                modifier = Modifier.requiredWidth(MEASUREMENT_FIELD_WIDTH.dp),
                value = left,
                onValueChange = onLeftChange,
                placeholderStringResKey = TextResKeys.LEFT,
                label = title
            )

            MeasurementTextField(
                modifier = Modifier.requiredWidth(MEASUREMENT_FIELD_WIDTH.dp),
                value = right,
                onValueChange = onRightChange,
                placeholderStringResKey = TextResKeys.RIGHT,
                label = title
            )
        }
    }
}

@Composable
private fun MeasurementTextField(
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholderStringResKey: String? = null,
    value: String,
    onValueChange: (String) -> Unit
) {
    val placeholderText = placeholderStringResKey?.let { getStringRes(it) }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it.convertToDouble().toString()) },
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
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewBodySizes() {
    BodyMeasurementDetails({}, {}, listOf(), null)
}
