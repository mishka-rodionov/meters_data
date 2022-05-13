package com.rodionov.meter_creator.di

import com.rodionov.database.dao.MeterDao
import com.rodionov.meter_creator.data.repository_impl.CreatorRepositoryImpl
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCreatorRepository(meterDao: MeterDao): CreatorRepository = CreatorRepositoryImpl(meterDao)

}