package com.mutkuensert.fitnesspursuit.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mutkuensert.fitnesspursuit.data.BodySizesModel
import java.time.LocalDateTime
import java.util.*

@Dao
interface BodySizesDao {

    @Query("SELECT * FROM BodySizes")
    suspend fun getAllBodySizes(): List<BodySizesModel>

    @Query("SELECT * FROM BodySizes WHERE id = :bodySizeId")
    suspend fun loadBodySizesById(bodySizeId: Int): List<BodySizesModel>

    @Query("SELECT * FROM BodySizes WHERE athleteName LIKE :athleteName")
    suspend fun loadAllBodySizesByName(athleteName: String): List<BodySizesModel>

    @Query("SELECT * FROM BodySizes WHERE athleteName LIKE :athleteName AND date = :targetDate")
    suspend fun findBodySizesOfUserOnDate(athleteName: String, targetDate: LocalDateTime): BodySizesModel

    @Insert
    suspend fun saveBodySize(bodySize:BodySizesModel)
}
