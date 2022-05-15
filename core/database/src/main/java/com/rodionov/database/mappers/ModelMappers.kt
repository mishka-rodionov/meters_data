package com.rodionov.database.mappers

import com.rodionov.database.entities.FlatEntity
import com.rodionov.database.entities.UserEntity
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.User

fun User.toEntity() = UserEntity(id, firstName, lastName, email, phone)
fun UserEntity.toModel() = User(id, firstName, lastName, email, phone)

fun Flat.toEntity() = FlatEntity(id, name, address, meters?.map { it.id })
fun FlatEntity.toModel(extractor: (List<String>?) -> List<Meter>?) = Flat(id, name, address, extractor.invoke(meters))
