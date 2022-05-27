package com.rodionov.meter_creator.di

import com.rodionov.base.state.ErrorHandler
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.dao.MeterInfoDao
import com.rodionov.meter_creator.data.repository_impl.CreatorRepositoryImpl
import com.rodionov.meter_creator.data.repository_impl.EditorRepositoryImpl
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import com.rodionov.meter_creator.domain.repository.EditorRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCreatorRepository(
        meterDao: MeterDao,
        flatDao: FlatDao,
        meterInfoDao: MeterInfoDao,
        errorHandler: ErrorHandler
    ): CreatorRepository = CreatorRepositoryImpl(meterDao, flatDao, meterInfoDao, errorHandler)

    @Provides
    fun provideEditorRepository(meterDao: MeterDao, errorHandler: ErrorHandler): EditorRepository =
        EditorRepositoryImpl(meterDao, errorHandler)

}