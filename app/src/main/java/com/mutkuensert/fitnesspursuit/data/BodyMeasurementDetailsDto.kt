package com.mutkuensert.fitnesspursuit.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

const val TABLE_NAME_BODY_MEASUREMENTS = "body_measurements"

@Entity(tableName = TABLE_NAME_BODY_MEASUREMENTS)
data class BodyMeasurementDetailsDto(
    val athleteName: String,
    val date: LocalDateTime,
    val weight: Double? = null,
    val leftBicep: Double? = null,
    val rightBicep: Double? = null,
    val leftForearm: Double? = null,
    val rightForearm: Double? = null,
    val leftCalf: Double? = null,
    val rightCalf: Double? = null,
    val leftThigh: Double? = null,
    val rightThigh: Double? = null,
    val chest: Double? = null,
    val hips: Double? = null,
    val neck: Double? = null,
    val shoulders: Double? = null,
    val waist: Double? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
