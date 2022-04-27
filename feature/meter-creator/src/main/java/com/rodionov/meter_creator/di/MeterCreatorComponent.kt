package com.rodionov.meter_creator.di

import dagger.Component

@Component(dependencies = [MeterCreatorDeps::class], modules = [MeterCreatorViewModelModule::class])
interface MeterCreatorComponent {
}