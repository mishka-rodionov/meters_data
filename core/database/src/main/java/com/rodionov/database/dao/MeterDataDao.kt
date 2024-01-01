package com.rodionov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.database.entities.MeterDataEntity

@Dao
interface MeterDataDao {

    @Insert(entity = MeterDataEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun saveMeterData(meterDataEntity: MeterDataEntity)

    @Query("SELECT meter_value FROM meters_data WHERE meter_id = :meterId ORDER BY meter_value DESC LIMIT 1")
    fun getLastMeterData(meterId: String): Double

}