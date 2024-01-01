package com.rodionov.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.dao.MeterDataDao
import com.rodionov.database.dao.MeterInfoDao
import com.rodionov.database.dao.UserDao
import com.rodionov.database.entities.*

@Database(
    entities = [
        MeterEntity::class,
        MeterDataEntity::class,
        FlatEntity::class,
        UserEntity::class,
        MeterInfoEntity::class],
    version = MetersDataDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class MetersDataDatabase : RoomDatabase() {

    abstract fun meterDao(): MeterDao
    abstract fun userDao(): UserDao
    abstract fun flatDao(): FlatDao
    abstract fun meterInfoDao(): MeterInfoDao
    abstract fun meterDataDao(): MeterDataDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MetersDataDatabase"
    }

}