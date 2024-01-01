package com.rodionov.meter_data_input.di

import com.rodionov.meter_data_input.presentation.apartments.ApartmentsFragment
import com.rodionov.meter_data_input.presentation.meter_input.MeterInputFragment
import com.rodionov.meter_data_input.presentation.meters.MetersFragment
import com.rodionov.meter_data_input.presentation.start_input.StartInputFragment
import dagger.Component

@Component(
    dependencies = [MeterDataInputDeps::class],
    modules = [MeterDataInputViewModelModule::class, MeterDataInputRepositoryModule::class]
)
interface MeterDataInputComponent {

    fun inject(startInputFragment: StartInputFragment)
    fun inject(metersFragment: MetersFragment)
    fun inject(apartmentsFragment: ApartmentsFragment)
    fun inject(meterInputFragment: MeterInputFragment)

    @Component.Builder
    interface Builder {

        fun deps(deps: MeterDataInputDeps): Builder

        fun build(): MeterDataInputComponent

    }

}