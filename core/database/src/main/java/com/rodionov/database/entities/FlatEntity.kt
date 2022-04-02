package com.rodionov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flats")
data class FlatEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "rooms")
    val rooms: List<String>
)
