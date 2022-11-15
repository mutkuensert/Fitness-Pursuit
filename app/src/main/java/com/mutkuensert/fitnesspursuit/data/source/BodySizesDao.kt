package com.mutkuensert.fitnesspursuit.data.source

import androidx.room.Dao
import androidx.room.Query
import com.mutkuensert.fitnesspursuit.data.BodySizes
import java.util.*

@Dao
interface BodySizesDao {

    @Query("SELECT * FROM bodysizes")
    suspend fun getAllBodySizes(): List<BodySizes>

    @Query("SELECT * FROM bodysizes WHERE id = :bodySizeId")
    suspend fun loadBodySizesById(bodySizeId: Int): List<BodySizes>

    @Query("SELECT * FROM bodysizes WHERE athleteName LIKE :athleteName")
    suspend fun loadAllBodySizesByName(athleteName: String): List<BodySizes>

    @Query("SELECT * FROM bodysizes WHERE athleteName LIKE :athleteName AND date = :targetDate")
    suspend fun findBodySizesOfUserOnDate(athleteName: String, targetDate: Date): BodySizes
}