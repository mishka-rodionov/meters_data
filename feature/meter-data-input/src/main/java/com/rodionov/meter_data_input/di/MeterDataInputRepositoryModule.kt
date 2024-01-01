package com.rodionov.meter_data_input.di

import com.rodionov.meter_data_input.data.repository_impl.MeterInputRepositoryImpl
import com.rodionov.meter_data_input.domain.repository.MeterInputRepository
import dagger.Binds
import dagger.Module

@Module
interface MeterDataInputRepositoryModule {

    @Binds
    fun bindsMeterDataInputRepository(meterInputRepositoryImpl: MeterInputRepositoryImpl): MeterInputRepository

}