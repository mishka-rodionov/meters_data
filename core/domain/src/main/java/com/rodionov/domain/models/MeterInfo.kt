package com.rodionov.domain.models

data class MeterInfo(
    val meterId: String,
    var dataSendDay: Int,
    var payRate: Double,
    var dataSendType: String, //TODO: type must be enum
    var email: String? = null,
    var company: String //TODO: company may be data class in further
)