package com.mutkuensert.fitnesspursuit.features.bodymeasurements.measurements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.fitnesspursuit.data.BodyMeasurementDetailsDto
import com.mutkuensert.fitnesspursuit.data.source.BodyMeasurementsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyMeasurementsViewModel @Inject constructor(private val bodySizeDao: BodyMeasurementsDao) :
    ViewModel() {
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

    fun onDeleteItem(bodyMeasurementDetailsDto: BodyMeasurementDetailsDto) {
        viewModelScope.launch {
            delay(100L)
            bodySizeDao.deleteBodyMeasurementDetails(bodyMeasurementDetailsDto)
            updateBodyMeasurements()
        }
    }
}
