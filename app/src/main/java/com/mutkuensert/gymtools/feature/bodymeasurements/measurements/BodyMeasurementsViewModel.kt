package com.mutkuensert.gymtools.feature.bodymeasurements.measurements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.gymtools.data.BodyMeasurementDetailsDto
import com.mutkuensert.gymtools.data.source.BodyMeasurementsDao
import com.mutkuensert.gymtools.navigation.navigators.BodyMeasurementsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BodyMeasurementsViewModel @Inject constructor(
    private val bodySizeDao: BodyMeasurementsDao,
    private val bodyMeasurementsNavigator: BodyMeasurementsNavigator
) : ViewModel() {
    private val _bodyMeasurements = MutableStateFlow(listOf<BodyMeasurementDetailsDto>())
    val bodyMeasurements = _bodyMeasurements.asStateFlow()

    private val _athleteNames = MutableStateFlow(listOf<String>())
    val athleteNames = _athleteNames.asStateFlow()

    fun updateBodyMeasurements() {
        viewModelScope.launch {
            _bodyMeasurements.value = bodySizeDao.getAllBodyMeasurements()
            _athleteNames.value = _bodyMeasurements.value.map { it.athleteName }.distinct()
        }
    }

    fun onNameSearch(name: String) {
        viewModelScope.launch {
            _bodyMeasurements.value = bodySizeDao.getAllBodyMeasurementDetailsByName("%$name%")
        }
    }

    fun navigateToBodyMeasurementDetails(entityId: Int? = null) {
        bodyMeasurementsNavigator.navigateToDetails(entityId)
    }

    fun onDeleteItem(bodyMeasurementDetailsDto: BodyMeasurementDetailsDto) {
        viewModelScope.launch {
            delay(100L)
            bodySizeDao.deleteBodyMeasurementDetails(bodyMeasurementDetailsDto)
            updateBodyMeasurements()
        }
    }
}
