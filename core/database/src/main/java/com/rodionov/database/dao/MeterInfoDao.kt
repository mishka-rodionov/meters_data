package com.rodionov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.database.entities.MeterInfoEntity

@Dao
interface MeterInfoDao {

    @Insert(entity = MeterInfoEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun setMeterInfoEntity(meterInfoEntity: MeterInfoEntity)

    @Query("SELECT * FROM meter_info")
    fun getMeterInfoEntities(): List<MeterInfoEntity>

    @Query("SELECT * FROM meter_info WHERE meterId IN (:ids) ")
    fun getMeterInfoEntities(ids: List<String>): List<MeterInfoEntity>

    @Query("SELECT * FROM meter_info WHERE meterId = :id")
    fun getMeterInfoEntity(id: String): MeterInfoEntity

    @Query("DELETE FROM meter_info")
    fun clearAllMeterInfoEntities()

}