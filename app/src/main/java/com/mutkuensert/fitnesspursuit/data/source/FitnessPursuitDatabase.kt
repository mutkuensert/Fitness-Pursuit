package com.mutkuensert.fitnesspursuit.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mutkuensert.fitnesspursuit.data.BodySizesModel
import com.mutkuensert.fitnesspursuit.core.Converters

@Database(entities = [BodySizesModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FitnessPursuitDatabase : RoomDatabase() {
    abstract fun bodySizesDao(): BodySizesDao
}
