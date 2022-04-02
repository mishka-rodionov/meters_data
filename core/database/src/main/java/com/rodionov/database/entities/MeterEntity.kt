package com.rodionov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rodionov.database.converters.DateConverter
import com.rodionov.database.converters.DomainConverter
import com.rodionov.domain.models.MeterUnits
import java.util.*

@Entity(tableName = "meters")
@TypeConverters(value = [DateConverter::class, DomainConverter::class])
data class MeterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "serial_number")
    val serialNumber: String,
    @ColumnInfo(name = "meter_unit")
    val meterUnit: MeterUnits,
    @ColumnInfo(name = "date_of_manufacturer")
    val dateOfManufacture: Date?,
    @ColumnInfo(name = "date_of_verification")
    val dateOfVerification: Date?
)