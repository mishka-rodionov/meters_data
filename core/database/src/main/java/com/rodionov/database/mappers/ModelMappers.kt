package com.rodionov.database.mappers

import com.rodionov.database.entities.FlatEntity
import com.rodionov.database.entities.MeterEntity
import com.rodionov.database.entities.MeterInfoEntity
import com.rodionov.database.entities.UserEntity
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo
import com.rodionov.domain.models.User

fun User.toEntity() = UserEntity(id, firstName, lastName, email, phone)
fun UserEntity.toModel() = User(id, firstName, lastName, email, phone)

fun Flat.toEntity() = FlatEntity(id, name, address, meters?.map { it.id }?.toMutableList())
suspend fun FlatEntity.toModel(extractor: suspend (List<String>?) -> List<Meter>?) = Flat(id, name, address, extractor.invoke(meters)?.toMutableList())

fun Meter.toEntity() = MeterEntity(id, name, type, serialNumber, meterUnit, dateOfManufacture, dateOfVerification)
fun MeterEntity.toModel() = Meter(id, name, type, serialNumber, meterUnit, dateOfManufacture, dateOfVerification)

fun MeterInfo.toEntity() = MeterInfoEntity(meterId, dataSendDay, payRate, dataSendType, email, company)
fun MeterInfoEntity.toModel() = MeterInfo(meterId, dataSendDay, payRate, dataSendType, email, company)