package com.rodionov.domain.models

import com.rodionov.database.entities.UserEntity

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String
)

fun User.toEntity() = UserEntity(id, firstName, lastName, email, phone)
