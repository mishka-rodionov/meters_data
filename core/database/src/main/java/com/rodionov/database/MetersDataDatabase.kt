package com.rodionov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.entities.FlatEntity
import com.rodionov.database.entities.MeterDataEntity
import com.rodionov.database.entities.MeterEntity
import com.rodionov.database.entities.RoomEntity

@Database(
    entities = [MeterEntity::class, MeterDataEntity::class, RoomEntity::class, FlatEntity::class],
    version = MetersDataDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class MetersDataDatabase: RoomDatabase() {

    abstract fun meterDao() : MeterDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MetersDataDatabase"
    }

}