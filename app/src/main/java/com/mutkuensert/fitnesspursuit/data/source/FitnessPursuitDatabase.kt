package com.mutkuensert.fitnesspursuit.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mutkuensert.fitnesspursuit.core.Converters
import com.mutkuensert.fitnesspursuit.data.BodyMeasurementDetailsDto

@Database(entities = [BodyMeasurementDetailsDto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FitnessPursuitDatabase : RoomDatabase() {
    abstract fun bodySizesDao(): BodyMeasurementsDao
}
