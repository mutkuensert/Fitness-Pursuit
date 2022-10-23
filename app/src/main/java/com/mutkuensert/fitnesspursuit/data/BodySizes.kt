package com.mutkuensert.fitnesspursuit.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class BodySizes(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val athleteName: String,
    val date: Date,
    val neck: Double?,
    val shoulder: Double?,
    val chest: Double?,
    val leftArmRelaxed: Double?,
    val rightArmRelaxed: Double?,
    val leftArmFlexed: Double?,
    val rightArmFlexed: Double?,
    val leftForearm: Double?,
    val rightForearm: Double?,
    val waist: Double?,
    val hip: Double?,
    val leftLeg: Double?,
    val rightLeg: Double?,
    val leftCalf: Double?,
    val rightCalf: Double?
)
