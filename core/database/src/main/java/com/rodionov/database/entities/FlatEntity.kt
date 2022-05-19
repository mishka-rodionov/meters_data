package com.rodionov.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rodionov.database.converters.ListConverter

@Entity(tableName = "flats")
@TypeConverters(ListConverter::class)
data class FlatEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "meters")
    val meters: MutableList<String>?
)
