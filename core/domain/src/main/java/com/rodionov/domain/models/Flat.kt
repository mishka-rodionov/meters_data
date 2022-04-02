package com.rodionov.domain.models

data class Flat(
    val id: String,
    val name: String,
    val address: String,
    val rooms: List<Room>
)
