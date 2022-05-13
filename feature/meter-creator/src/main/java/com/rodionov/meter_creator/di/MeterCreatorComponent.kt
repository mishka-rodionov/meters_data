package com.rodionov.meter_creator.di

import com.rodionov.meter_creator.presentation.flat_creator.FlatCreatorFragment
import dagger.Component

@Component(dependencies = [MeterCreatorDeps::class], modules = [MeterCreatorViewModelModule::class, RepositoryModule::class])
interface MeterCreatorComponent {

    fun inject(flatCreatorFragment: FlatCreatorFragment)

    @Component.Builder
    interface Builder {

        fun deps(meterCreatorDeps: MeterCreatorDeps): Builder

        fun build(): MeterCreatorComponent

    }

}