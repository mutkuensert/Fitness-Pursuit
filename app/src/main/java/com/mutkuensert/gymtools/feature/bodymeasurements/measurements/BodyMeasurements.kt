package com.mutkuensert.gymtools.feature.bodymeasurements.measurements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mutkuensert.gymtools.R
import com.mutkuensert.gymtools.components.AutoCompleteTextField
import com.mutkuensert.gymtools.core.getStringRes
import com.mutkuensert.gymtools.data.BodyMeasurementDetailsDto
import com.mutkuensert.gymtools.resources.TextResKeys
import com.mutkuensert.gymtools.ui.theme.AppColors
import java.time.LocalDateTime

private const val VERTICAL_SPACE = 20
private const val OVERALL_WIDTH = 280

@Composable
fun BodyMeasurementsScreen(
    viewModel: BodyMeasurementsViewModel = hiltViewModel()
) {
    val bodyMeasurements = viewModel.bodyMeasurements.collectAsStateWithLifecycle()
    val athleteNames = viewModel.athleteNames.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BodyMeasurements(
            navigateToBodyMeasurementDetails = viewModel::navigateToBodyMeasurementDetails,
            bodyMeasurements = bodyMeasurements.value,
            athleteNames = athleteNames.value,
            onNameSearch = viewModel::onNameSearch,
            onDeleteItem = viewModel::onDeleteItem
        )
    }

    LaunchedEffect(true) {
        viewModel.updateBodyMeasurements()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BodyMeasurements(
    navigateToBodyMeasurementDetails: (id: Int?) -> Unit,
    bodyMeasurements: List<BodyMeasurementDetailsDto>,
    athleteNames: List<String>,
    onNameSearch: (String) -> Unit,
    onDeleteItem: (BodyMeasurementDetailsDto) -> Unit
) {
    Column(
        modifier = Modifier.requiredWidth(OVERALL_WIDTH.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(VERTICAL_SPACE.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        IconButton(onClick = { navigateToBodyMeasurementDetails(null) }) {
            Icon(
                modifier = Modifier.alpha(0.5f),
                painter = painterResource(R.drawable.add),
                contentDescription = getStringRes(TextResKeys.ADD_NEW)
            )
        }

        val (name, onNameChange) = remember { mutableStateOf("") }

        AutoCompleteTextField(
            value = name,
            onValueChange = {
                onNameChange(it)
                onNameSearch(it)
            },
            data = athleteNames
        )

        val infoKeysToRadioOptions = listOf(
            TextResKeys.WEIGHT to getStringRes(TextResKeys.WEIGHT),
            TextResKeys.BICEP to getStringRes(TextResKeys.BICEP),
            TextResKeys.FOREARM to getStringRes(TextResKeys.FOREARM),
            TextResKeys.CALF to getStringRes(TextResKeys.CALF),
            TextResKeys.THIGH to getStringRes(TextResKeys.THIGH),
            TextResKeys.CHEST to getStringRes(TextResKeys.CHEST),
            TextResKeys.HIPS to getStringRes(TextResKeys.HIPS),
            TextResKeys.NECK to getStringRes(TextResKeys.NECK),
            TextResKeys.SHOULDERS to getStringRes(TextResKeys.SHOULDERS),
            TextResKeys.WAIST to getStringRes(TextResKeys.WAIST),
        )
        val selectedInfoIndex = remember { mutableIntStateOf(0) }

        InfoSelector(
            infoKeysToRadioOptions = infoKeysToRadioOptions,
            selectionIndex = selectedInfoIndex
        )

        LazyColumn {
            items(
                items = bodyMeasurements,
                key = {
                    it.id
                }
            ) { bodyMeasurementDetailsDto ->
                var isVisible by remember { mutableStateOf(true) }
                val dismissState = rememberDismissState(confirmStateChange = { dismissValue ->
                    if (dismissValue == DismissValue.DismissedToEnd || dismissValue == DismissValue.DismissedToStart) {
                        onDeleteItem(bodyMeasurementDetailsDto)
                        isVisible = false
                        true
                    } else {
                        isVisible = true
                        false
                    }
                })

                AnimatedVisibility(isVisible) {
                    SwipeToDismiss(
                        state = dismissState,
                        background = { SwipeBackground() },
                        dismissThresholds = { FractionalThreshold(0.70f) },
                        dismissContent = {
                            SearchResultItem(
                                bodyMeasurementDetails = bodyMeasurementDetailsDto,
                                selectedAthleteInfoKey = infoKeysToRadioOptions[selectedInfoIndex.value].first,
                                navigateToBodyMeasurementDetails = navigateToBodyMeasurementDetails
                            )
                        }
                    )
                }
                Spacer(Modifier.height(10.dp))
            }
        }

        Spacer(modifier = Modifier.height(VERTICAL_SPACE.dp))
    }
}

@Composable
private fun SwipeBackground() {
    Row(
        modifier = Modifier
            .background(color = AppColors.burntRed, shape = RoundedCornerShape(8.dp))
            .fillMaxSize()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DeleteIcon()
        DeleteIcon()
    }
}

@Composable
private fun DeleteIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_delete),
        contentDescription = null,
        tint = Color.White
    )
}

@Composable
fun InfoSelector(
    infoKeysToRadioOptions: List<Pair<String, String>>,
    selectionIndex: MutableState<Int>
) {
    LazyRow(Modifier.selectableGroup()) {
        itemsIndexed(infoKeysToRadioOptions.map { it.second }) { index, text ->
            Row(
                Modifier
                    .selectable(
                        selected = (index == selectionIndex.value),
                        onClick = { selectionIndex.value = index },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (index == selectionIndex.value),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(selectedColor = AppColors.metalGray)
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    bodyMeasurementDetails: BodyMeasurementDetailsDto,
    selectedAthleteInfoKey: String,
    navigateToBodyMeasurementDetails: (id: Int?) -> Unit
) {
    Column(
        modifier = Modifier
            .width(OVERALL_WIDTH.dp)
            .shadow(shape = RoundedCornerShape(8.dp), elevation = 5.dp)
            .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .clickable { navigateToBodyMeasurementDetails(bodyMeasurementDetails.id) }
            .padding(5.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = bodyMeasurementDetails.athleteName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
        )

        val context = LocalContext.current
        val selectedAthleteInfo = try {
            with(TextResKeys) {
                when (selectedAthleteInfoKey) {
                    WEIGHT -> getStringRes(
                        context,
                        WEIGHT
                    ) + ": " + bodyMeasurementDetails.weight!!.toString()

                    BICEP -> getStringRes(
                        context = context,
                        key = SELECTED_ATHLETE_INFO_LR,
                        args = arrayOf(
                            getStringRes(context, BICEP),
                            bodyMeasurementDetails.leftBicep!!,
                            bodyMeasurementDetails.rightBicep!!
                        )
                    )

                    FOREARM -> getStringRes(
                        context = context,
                        key = SELECTED_ATHLETE_INFO_LR,
                        args = arrayOf(
                            getStringRes(context, FOREARM),
                            bodyMeasurementDetails.leftForearm!!,
                            bodyMeasurementDetails.rightForearm!!
                        )
                    )

                    CALF -> getStringRes(
                        context = context,
                        key = SELECTED_ATHLETE_INFO_LR,
                        args = arrayOf(
                            getStringRes(context, CALF),
                            bodyMeasurementDetails.leftCalf!!,
                            bodyMeasurementDetails.rightCalf!!
                        )
                    )

                    THIGH -> getStringRes(
                        context = context,
                        key = SELECTED_ATHLETE_INFO_LR,
                        args = arrayOf(
                            getStringRes(context, THIGH),
                            bodyMeasurementDetails.leftThigh!!,
                            bodyMeasurementDetails.rightThigh!!
                        )
                    )

                    CHEST -> getStringRes(
                        context,
                        CHEST
                    ) + ": " + bodyMeasurementDetails.chest!!.toString()

                    HIPS -> getStringRes(
                        context,
                        HIPS
                    ) + ": " + bodyMeasurementDetails.hips!!.toString()

                    NECK -> getStringRes(
                        context,
                        NECK
                    ) + ": " + bodyMeasurementDetails.neck!!.toString()

                    SHOULDERS -> {
                        getStringRes(
                            context,
                            SHOULDERS
                        ) + ": " + bodyMeasurementDetails.shoulders!!.toString()
                    }

                    WAIST -> getStringRes(
                        context,
                        WAIST
                    ) + ": " + bodyMeasurementDetails.waist!!.toString()

                    else -> ""
                }
            }
        } catch (error: NullPointerException) {
            ""
        }

        Text(text = selectedAthleteInfo)

        with(bodyMeasurementDetails.date) { Text(text = "$dayOfMonth.$monthValue.$year") }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewBodySizeList() {
    val fakeData = BodyMeasurementDetailsDto(
        athleteName = "Athlete name",
        date = LocalDateTime.now(),
        weight = 80.0,
        leftBicep = 40.0,
        rightBicep = 40.0,
        leftForearm = 30.0,
        rightForearm = 30.0,
        leftCalf = 50.0,
        rightCalf = 50.0,
        leftThigh = 100.0,
        rightThigh = 100.0,
        chest = 200.0,
        hips = 80.0,
        neck = 60.0,
        shoulders = 130.0,
        waist = 80.0
    )
    val list = mutableListOf<BodyMeasurementDetailsDto>()

    repeat(50) {
        list.add(fakeData.copy().apply { id = it })
    }

    BodyMeasurements(
        navigateToBodyMeasurementDetails = { },
        bodyMeasurements = list,
        athleteNames = list.map { it.athleteName },
        onNameSearch = {},
        onDeleteItem = {}
    )
}
