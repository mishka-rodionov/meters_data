package com.rodionov.meter_creator.di

import dagger.Component

@Component(dependencies = [MeterCreatorDeps::class], modules = [MeterCreatorViewModelModule::class])
interface MeterCreatorComponent {

    @Component.Builder
    interface Builder {

        fun deps(meterCreatorDeps: MeterCreatorDeps): Builder

        fun build(): MeterCreatorComponent

    }

}