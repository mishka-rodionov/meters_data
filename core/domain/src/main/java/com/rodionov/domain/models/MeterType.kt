package com.rodionov.domain.models

enum class MeterType(val meterName: String, val units: MeterUnits) {
    COLD_WATER(meterName = "Холодная вода", units = MeterUnits.M3),
    HOT_WATER(meterName = "Горячая вода", units = MeterUnits.M3),
    GAS(meterName = "Газ", units = MeterUnits.M3),
    ELECTRIC(meterName = "Электричество", units = MeterUnits.kWh)
}