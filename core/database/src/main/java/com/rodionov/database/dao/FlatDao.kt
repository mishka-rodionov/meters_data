package com.rodionov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.database.entities.FlatEntity

@Dao
interface FlatDao {

    @Insert(entity = FlatEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFlatEntity(flat: FlatEntity)

    @Query("SELECT * FROM flats")
    suspend fun getFlats(): List<FlatEntity>

    @Query("SELECT * FROM flats WHERE id = :id")
    suspend fun getFlat(id: String): FlatEntity

    @Query("DELETE FROM flats")
    suspend fun clearAll()

}