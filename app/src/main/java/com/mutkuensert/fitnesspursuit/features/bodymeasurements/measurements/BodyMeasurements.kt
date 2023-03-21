package com.mutkuensert.fitnesspursuit.features.bodymeasurements.measurements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mutkuensert.fitnesspursuit.R
import com.mutkuensert.fitnesspursuit.components.AutoCompleteTextField
import com.mutkuensert.fitnesspursuit.components.ShadowedCard
import com.mutkuensert.fitnesspursuit.core.getStringRes
import com.mutkuensert.fitnesspursuit.data.BodyMeasurementDetailsDto
import com.mutkuensert.fitnesspursuit.resources.TextResKeys
import java.time.LocalDateTime

private const val HORIZONTAL_SPACE = 10
private const val VERTICAL_SPACE = 20
private const val OVERALL_WIDTH = 280

@Composable
fun BodyMeasurementsScreen(
    navigateToBodyMeasurementDetails: (id: Int?) -> Unit,
    viewModel: BodyMeasurementsViewModel = hiltViewModel()
) {
    val bodySizeList = viewModel.bodySizeList.collectAsStateWithLifecycle()
    val athleteNames = viewModel.athleteNames.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BodyMeasurements(
            navigateToBodyMeasurementDetails,
            bodySizeList.value,
            athleteNames.value,
            viewModel::onNameSearch
        )
    }
}

@Composable
fun BodyMeasurements(
    navigateToBodyMeasurementDetails: (id: Int?) -> Unit,
    bodySizeList: List<BodyMeasurementDetailsDto>,
    athleteNames: List<String>,
    onNameSearch: (String) -> Unit
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
            items(bodySizeList) {
                SearchResultItem(
                    bodySizes = it,
                    navigateToBodyMeasurementDetails = navigateToBodyMeasurementDetails
                )

                Spacer(Modifier.height(10.dp))
            }
        }

        Spacer(modifier = Modifier.height(VERTICAL_SPACE.dp))
    }
}

@Composable
fun SearchResultItem(
    bodySizes: BodyMeasurementDetailsDto,
    navigateToBodyMeasurementDetails: (id: Int?) -> Unit
) {
    ShadowedCard {
        Row(
            modifier = Modifier.clickable { navigateToBodyMeasurementDetails(bodySizes.id) },
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
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewBodySizeList() {
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

    BodyMeasurements(navigateToBodyMeasurementDetails = { }, list, list.map { it.athleteName }, {})
}
