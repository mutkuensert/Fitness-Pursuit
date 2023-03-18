package com.mutkuensert.fitnesspursuit.features.bodysizes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.fitnesspursuit.data.BodySizesModel
import com.mutkuensert.fitnesspursuit.data.source.BodySizesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BodySizesViewModel @Inject constructor(
    private val bodySizesDao: BodySizesDao
) : ViewModel() {

    fun saveData(
        athleteName: String,
        date: LocalDateTime,
        leftBicep: String?,
        rightBicep: String?,
        leftForearm: String?,
        rightForearm: String?,
        leftCalf: String?,
        rightCalf: String?,
        leftThigh: String?,
        rightThigh: String?,
        chest: String?,
        hips: String?,
        neck: String?,
        shoulders: String?,
        waist: String?
    ) {
        viewModelScope.launch {
            bodySizesDao.saveBodySize(
                BodySizesModel(
                    athleteName = athleteName,
                    date = date,
                    leftBicep = leftBicep?.toDouble(),
                    rightBicep = rightBicep?.toDouble(),
                    leftForearm = leftForearm?.toDouble(),
                    rightForearm = rightForearm?.toDouble(),
                    leftCalf = leftCalf?.toDouble(),
                    rightCalf = rightCalf?.toDouble(),
                    leftThigh = leftThigh?.toDouble(),
                    rightThigh = rightThigh?.toDouble(),
                    chest = chest?.toDouble(),
                    hips = hips?.toDouble(),
                    neck = neck?.toDouble(),
                    shoulders = shoulders?.toDouble(),
                    waist = waist?.toDouble()
                )
            )
        }
    }
}
