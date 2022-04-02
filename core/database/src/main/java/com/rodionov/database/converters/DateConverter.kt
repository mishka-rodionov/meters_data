package com.rodionov.database.converters

import androidx.room.TypeConverter
import com.rodionov.utils.DateTimeFormatter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromDate(date: Date?) = if (date != null) DateTimeFormatter.format(date) else null

    @TypeConverter
    fun toDate(date: String?) = if (date != null) DateTimeFormatter.parse(date) else null

}