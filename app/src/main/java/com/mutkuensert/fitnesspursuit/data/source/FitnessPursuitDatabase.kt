package com.mutkuensert.fitnesspursuit.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mutkuensert.fitnesspursuit.data.BodySizes
import com.mutkuensert.fitnesspursuit.util.Converters

@Database(entities = [BodySizes::class], version = 1)
@TypeConverters(Converters::class)
abstract class FitnessPursuitDatabase : RoomDatabase() {
    abstract fun bodySizesDao() : BodySizesDao
}