package com.rodionov.meters_data.data.di

import android.content.Context
import androidx.room.Room
import com.rodionov.database.MetersDataDatabase
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.dao.MeterInfoDao
import com.rodionov.database.dao.UserDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): MetersDataDatabase {
        return Room.databaseBuilder(context, MetersDataDatabase::class.java, MetersDataDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: MetersDataDatabase): UserDao = database.userDao()

    @Provides
    fun provideMeterDao(database: MetersDataDatabase): MeterDao = database.meterDao()

    @Provides
    fun provideFlatDao(database: MetersDataDatabase): FlatDao = database.flatDao()

    @Provides
    fun provideMeterInfoDao(database: MetersDataDatabase): MeterInfoDao = database.meterInfoDao()

}