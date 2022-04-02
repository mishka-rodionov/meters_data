package com.rodionov.domain.models

import java.util.*

data class Meter(
    val id: String,
    val serialNumber: String,
    val meterUnit: MeterUnits,
    val dateOfManufacture: Date?,
    val dateOfVerification: Date?
)
