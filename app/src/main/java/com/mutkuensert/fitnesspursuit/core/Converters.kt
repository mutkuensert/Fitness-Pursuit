package com.mutkuensert.fitnesspursuit.core

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: LocalDateTime?): Date? {
        return value?.let {
            Date(it.toInstant(ZoneOffset.of(ZoneOffset.systemDefault().id)).toEpochMilli())
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
