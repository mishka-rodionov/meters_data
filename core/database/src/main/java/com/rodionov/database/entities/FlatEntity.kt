package com.rodionov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "flats")
data class FlatEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "rooms")
    val rooms: List<String>
)
