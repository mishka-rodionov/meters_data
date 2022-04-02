package com.rodionov.database.converters

import androidx.room.TypeConverter
import com.rodionov.domain.models.MeterUnits

class DomainConverter {

    @TypeConverter
    fun fromMeterUnit(unit: MeterUnits) = unit.name

    @TypeConverter
    fun toMeterUnit(name: String) = MeterUnits.valueOf(name)

}