package com.rodionov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "meter_info")
data class MeterInfoEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "dataSendDay")
    var dataSendDay: Int,
    @ColumnInfo(name = "payRate")
    var payRate: Double,
    @ColumnInfo(name = "dataSendType")
    var dataSendType: String, //TODO: type must be enum
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "company")
    var company: String
)
