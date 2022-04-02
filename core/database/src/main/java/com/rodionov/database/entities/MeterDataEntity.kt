package com.rodionov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.rodionov.database.converters.DateConverter
import java.util.*

@Entity(tableName = "meters_data")
@TypeConverters(value = [DateConverter::class])
data class MeterDataEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "meter_id")
    val meterId: String,
    @ColumnInfo(name = "date_time")
    val dateTime: Date,
    @ColumnInfo(name = "meter_value")
    val meterValue: Double = 0.0
)
