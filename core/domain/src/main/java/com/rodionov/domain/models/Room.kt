package com.rodionov.domain.models

data class Room(
    val id: String,
    val name: String,
    val meters: List<Meter>
)
