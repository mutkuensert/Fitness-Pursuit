package com.mutkuensert.fitnesspursuit.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mutkuensert.fitnesspursuit.data.BodySizes
import com.mutkuensert.fitnesspursuit.util.Converters

@Database(entities = [BodySizes::class], version = 1)
@TypeConverters(Converters::class)
abstract class FitnessPursuitDatabase : RoomDatabase() {
    abstract fun bodySizesDao() : BodySizesDao

    companion object{
        @Volatile private var instance : FitnessPursuitDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            FitnessPursuitDatabase::class.java,"fitnesspursuitdatabase").build()
    }
}