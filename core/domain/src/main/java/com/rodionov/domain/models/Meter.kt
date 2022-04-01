package com.rodionov.domain.models

import java.util.*

data class Meter(
    val meterValue: Double,
    val meterUnit: MeterUnits,
    val date: Date
)
