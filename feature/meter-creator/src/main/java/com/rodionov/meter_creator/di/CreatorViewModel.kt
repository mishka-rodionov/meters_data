package com.rodionov.meter_creator.di

import androidx.lifecycle.ViewModel

class CreatorViewModel: ViewModel() {

    val creatorComponent = DaggerMeterCreatorComponent.builder().deps(MeterCreatorDepsStore.deps).build()

}