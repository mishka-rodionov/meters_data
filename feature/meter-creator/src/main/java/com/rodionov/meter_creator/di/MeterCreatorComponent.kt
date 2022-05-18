package com.rodionov.meter_creator.di

import com.rodionov.meter_creator.presentation.flat_creator.FlatCreatorFragment
import com.rodionov.meter_creator.presentation.meter_creator.MeterCreatorFragment
import dagger.Component

@Component(dependencies = [MeterCreatorDeps::class], modules = [MeterCreatorViewModelModule::class, RepositoryModule::class])
interface MeterCreatorComponent {

    fun inject(flatCreatorFragment: FlatCreatorFragment)
    fun inject(meterCreatorFragment: MeterCreatorFragment)

    @Component.Builder
    interface Builder {

        fun deps(meterCreatorDeps: MeterCreatorDeps): Builder

        fun build(): MeterCreatorComponent

    }

}