package com.rodionov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.database.entities.MeterEntity

@Dao
interface MeterDao {

    @Insert(entity = MeterEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun setMeterEntity(meterEntity: MeterEntity)

    @Query("SELECT * FROM meters")
    fun getMeterEntities(): List<MeterEntity>

    @Query("SELECT * FROM meters WHERE id IN (:ids) ")
    fun getMeterEntities(ids: List<String>): List<MeterEntity>

    @Query("SELECT * FROM meters WHERE id = :id")
    fun getMeterEntity(id: String): MeterEntity

    @Query("DELETE FROM meters")
    fun clearAllMeterEntities()

}