package com.rodionov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "rooms")
data class RoomEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "meters")
    val meters: List<String>
)
