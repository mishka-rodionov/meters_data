package com.rodionov.domain.models

import java.util.*

data class Meter(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: MeterType,
    val serialNumber: String,
    val meterUnit: MeterUnits,
    val dateOfManufacture: Date? = null,
    val dateOfVerification: Date? = null
)
