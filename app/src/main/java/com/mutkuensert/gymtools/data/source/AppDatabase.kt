package com.mutkuensert.gymtools.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mutkuensert.gymtools.core.Converters
import com.mutkuensert.gymtools.data.BodyMeasurementDetailsDto

@Database(entities = [BodyMeasurementDetailsDto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bodySizesDao(): BodyMeasurementsDao
}
