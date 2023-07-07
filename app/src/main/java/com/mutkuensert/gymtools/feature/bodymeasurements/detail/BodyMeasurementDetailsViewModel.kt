package com.mutkuensert.gymtools.feature.bodymeasurements.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.gymtools.data.BodyMeasurementDetailsDto
import com.mutkuensert.gymtools.data.source.BodyMeasurementsDao
import com.mutkuensert.gymtools.feature.bodymeasurements.navigation.ENTITY_ID
import com.mutkuensert.gymtools.navigation.navigators.BaseNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodyMeasurementDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bodyMeasurementsDao: BodyMeasurementsDao,
    private val baseNavigator: BaseNavigator
) : ViewModel() {
    private var entityId: String? = savedStateHandle[ENTITY_ID]

    private val _athleteNames = MutableStateFlow(listOf<String>())
    val athleteNames = _athleteNames.asStateFlow()

    private val _bodyMeasurementDetails = MutableStateFlow<BodyMeasurementDetailsDto?>(null)
    val bodyMeasurementDetails = _bodyMeasurementDetails.asStateFlow()

    init {
        viewModelScope.launch {
            _athleteNames.value =
                bodyMeasurementsDao.getAllBodyMeasurements().distinct().map { it.athleteName }
        }

        if (entityId != null) {
            viewModelScope.launch {
                _bodyMeasurementDetails.value =
                    bodyMeasurementsDao.getBodyMeasurementDetailsById(entityId!!.toInt())
            }
        }
    }

    fun navigateToBodyMeasurements() {
        baseNavigator.navigateUp()
    }

    fun saveData(bodyMeasurementDetails: BodyMeasurementDetailsDto) {
        viewModelScope.launch {
            if (entityId == null) {
                bodyMeasurementsDao.insertBodyMeasurementDetails(bodyMeasurementDetails)
            } else {
                bodyMeasurementsDao.updateBodyMeasurementDetails(
                    bodyMeasurementDetails.apply {
                        id = entityId!!.toInt()
                    }
                )
            }
        }
    }
}
