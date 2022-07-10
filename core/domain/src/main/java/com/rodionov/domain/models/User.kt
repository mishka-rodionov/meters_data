package com.rodionov.domain.models

data class User(
    val id: String,
    val login: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String? = null,
    val flatsId: MutableList<String>? = null
)
