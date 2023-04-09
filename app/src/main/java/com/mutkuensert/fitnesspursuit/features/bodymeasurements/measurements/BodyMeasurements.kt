package com.mutkuensert.fitnesspursuit.features.bodymeasurements.measurements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mutkuensert.fitnesspursuit.R
import com.mutkuensert.fitnesspursuit.components.AutoCompleteTextField
import com.mutkuensert.fitnesspursuit.core.getStringRes
import com.mutkuensert.fitnesspursuit.data.BodyMeasurementDetailsDto
import com.mutkuensert.fitnesspursuit.resources.TextResKeys
import com.mutkuensert.fitnesspursuit.ui.theme.AppColors
import java.time.LocalDateTime

private const val HORIZONTAL_SPACE = 10
private const val VERTICAL_SPACE = 20
private const val OVERALL_WIDTH = 280

@Composable
fun BodyMeasurementsScreen(
    navigateToBodyMeasurementDetails: (id: Int?) -> Unit,
    viewModel: BodyMeasurementsViewModel = hiltViewModel()
) {
    val bodyMeasurements = viewModel.bodyMeasurements.collectAsStateWithLifecycle()
    val athleteNames = viewModel.athleteNames.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BodyMeasurements(
            navigateToBodyMeasurementDetails,
            bodyMeasurements.value,
            athleteNames.value,
            viewModel::onNameSearch,
            viewModel::onDeleteItem
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
        Spacer(modifier = Modifier.height(100.dp))

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
                                bodySizes = bodyMeasurementDetailsDto,
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
            .background(color = AppColors.burntSienna, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
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
private fun SearchResultItem(
    bodySizes: BodyMeasurementDetailsDto,
    navigateToBodyMeasurementDetails: (id: Int?) -> Unit
) {
    Row(
        modifier = Modifier
            .shadow(shape = RoundedCornerShape(8.dp), elevation = 5.dp)
            .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .clickable { navigateToBodyMeasurementDetails(bodySizes.id) }
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(HORIZONTAL_SPACE.dp)
    ) {
        Text(
            text = bodySizes.athleteName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        with(bodySizes.date) { Text(text = "$dayOfMonth.$monthValue.$year") }
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
        list.add(fakeData)
    }

    BodyMeasurements(
        navigateToBodyMeasurementDetails = { },
        list,
        list.map { it.athleteName },
        {},
        {}
    )
}
