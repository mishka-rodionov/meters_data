package com.rodionov.domain.models

data class MeterInfo(
    var sendDataDay: Int,
    var payRate: Double,
    var dataSendType: String, //TODO: type must be enum
    var email: String,
    var company: String //TODO: company may be data class in further
)