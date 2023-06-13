package com.mutkuensert.fitnesspursuit.features.bodymeasurements.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.fitnesspursuit.data.BodyMeasurementDetailsDto
import com.mutkuensert.fitnesspursuit.data.source.BodyMeasurementsDao
import com.mutkuensert.fitnesspursuit.navigation.ATHLETE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyMeasurementDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bodyMeasurementsDao: BodyMeasurementsDao
) : ViewModel() {
    private var athleteId: String? = savedStateHandle[ATHLETE_ID]

    private val _athleteNames = MutableStateFlow(listOf<String>())
    val athleteNames = _athleteNames.asStateFlow()

    private val _bodyMeasurementDetails = MutableStateFlow<BodyMeasurementDetailsDto?>(null)
    val bodyMeasurementDetails = _bodyMeasurementDetails.asStateFlow()

    init {
        viewModelScope.launch {
            _athleteNames.value =
                bodyMeasurementsDao.getAllBodyMeasurements().distinct().map { it.athleteName }
        }

        if (athleteId != null) {
            viewModelScope.launch {
                _bodyMeasurementDetails.value =
                    bodyMeasurementsDao.getBodyMeasurementDetailsById(athleteId!!.toInt())
            }
        }
    }

    fun saveData(bodyMeasurementDetails: BodyMeasurementDetailsDto) {
        viewModelScope.launch {
            if (athleteId == null) {
                bodyMeasurementsDao.insertBodyMeasurementDetails(bodyMeasurementDetails)
            } else {
                bodyMeasurementsDao.updateBodyMeasurementDetails(
                    bodyMeasurementDetails.apply {
                        id = athleteId!!.toInt()
                    }
                )
            }
        }
    }
}
