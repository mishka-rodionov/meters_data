package com.rodionov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun setUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUser(id: String): UserEntity

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()

}