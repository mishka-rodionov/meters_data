package com.rodionov.meter_data_input.data.dto

import com.rodionov.domain.models.MeterType

data class MeterItem(
    val id: String,
    val meterType: MeterType,
    val name: String,
    val address: String,
    val lastData: String
)
