package com.rodionov.domain.models

import java.util.*

data class MeterData(
    val id: String,
    val meterId: String,
    val date: Date,
    val meterValue: Double = 0.0,
)
