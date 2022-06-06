package com.rodionov.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Meter(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val type: MeterType,
    val serialNumber: String,
    val meterUnit: MeterUnits,
    val dateOfManufacture: Date? = null,
    val dateOfVerification: Date? = null
): Parcelable
