package com.mutkuensert.fitnesspursuit.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mutkuensert.fitnesspursuit.data.BodyMeasurementDetailsDto
import com.mutkuensert.fitnesspursuit.data.TABLE_NAME_BODY_MEASUREMENTS
import java.time.LocalDateTime
import java.util.*

@Dao
interface BodyMeasurementsDao {

    @Query("SELECT * FROM $TABLE_NAME_BODY_MEASUREMENTS ORDER BY id DESC")
    suspend fun getAllBodyMeasurements(): List<BodyMeasurementDetailsDto>

    @Query("SELECT * FROM $TABLE_NAME_BODY_MEASUREMENTS WHERE id = :bodySizeId")
    suspend fun getBodyMeasurementDetailsById(bodySizeId: Int): BodyMeasurementDetailsDto

    @Query("SELECT * FROM $TABLE_NAME_BODY_MEASUREMENTS WHERE lower(athleteName) LIKE lower(:athleteName)")
    suspend fun getAllBodyMeasurementDetailsByName(athleteName: String): List<BodyMeasurementDetailsDto>

    @Query("SELECT * FROM $TABLE_NAME_BODY_MEASUREMENTS WHERE athleteName LIKE :athleteName AND date = :targetDate")
    suspend fun getBodyMeasurementDetailsByNameAndDate(athleteName: String, targetDate: LocalDateTime): BodyMeasurementDetailsDto

    @Insert
    suspend fun insertBodyMeasurementDetails(vararg bodySize: BodyMeasurementDetailsDto)

    @Update
    suspend fun updateBodyMeasurementDetails(vararg bodyMeasurementDetails: BodyMeasurementDetailsDto)
}
