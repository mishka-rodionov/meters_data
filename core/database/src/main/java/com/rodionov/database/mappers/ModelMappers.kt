package com.rodionov.database.mappers

import com.rodionov.database.entities.UserEntity
import com.rodionov.domain.models.User

fun User.toEntity() = UserEntity(id, firstName, lastName, email, phone)
fun UserEntity.toModel() = User(id, firstName, lastName, email, phone)
