package com.mutkuensert.fitnesspursuit.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "BodySizes")
data class BodySizesModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val athleteName: String,
    val date: Date,
    val leftBicep: Double?,
    val rightBicep: Double?,
    val leftForearm: Double?,
    val rightForearm: Double?,
    val leftCalf: Double?,
    val rightCalf: Double?,
    val leftThigh: Double?,
    val rightThigh: Double?,
    val chest: Double?,
    val hips: Double?,
    val neck: Double?,
    val shoulders: Double?,
    val waist: Double?
)
