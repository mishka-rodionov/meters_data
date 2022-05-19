package com.rodionov.domain.models

import java.util.*

data class Flat(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val address: String,
    val meters: MutableList<Meter>? = null
)
