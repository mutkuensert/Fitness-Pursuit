package com.mutkuensert.fitnesspursuit.features.bodymeasurements.measurements

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.fitnesspursuit.data.BodyMeasurementDetailsDto
import com.mutkuensert.fitnesspursuit.data.source.BodyMeasurementsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyMeasurementsViewModel @Inject constructor(private val bodySizeDao: BodyMeasurementsDao) :
    ViewModel() {
    private val _bodySizeList = MutableStateFlow(listOf<BodyMeasurementDetailsDto>())
    val bodySizeList = _bodySizeList.asStateFlow()

    private val _athleteNames = MutableStateFlow(listOf<String>())
    val athleteNames = _athleteNames.asStateFlow()

    init {
        viewModelScope.launch {
            _bodySizeList.value = bodySizeDao.getAllBodyMeasurements()
            _athleteNames.value = _bodySizeList.value.map { it.athleteName }.distinct()
        }
    }

    fun onNameSearch(name: String) {
        viewModelScope.launch {
            _bodySizeList.value = bodySizeDao.getAllBodyMeasurementDetailsByName("%$name%")
        }
    }
}
