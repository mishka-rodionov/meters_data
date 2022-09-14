package com.rodionov.meter_data_input.di

import androidx.lifecycle.ViewModel
import com.rodionov.base.factory.CommonViewModelKey
import com.rodionov.meter_data_input.presentation.apartments.ApartmentsViewModel
import com.rodionov.meter_data_input.presentation.meters.MetersViewModel
import com.rodionov.meter_data_input.presentation.start_input.StartInputViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MeterDataInputViewModelModule {

    @IntoMap
    @CommonViewModelKey(StartInputViewModel::class)
    @Provides
    fun provideStartInputViewModel(): ViewModel = StartInputViewModel()

    @IntoMap
    @CommonViewModelKey(MetersViewModel::class)
    @Provides
    fun provideMetersViewModel(): ViewModel = MetersViewModel()

    @IntoMap
    @CommonViewModelKey(ApartmentsViewModel::class)
    @Provides
    fun provideApartmentsViewModel(): ViewModel = ApartmentsViewModel()

}