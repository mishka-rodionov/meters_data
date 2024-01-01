package com.rodionov.meter_data_input.di

import androidx.lifecycle.ViewModel

class DataInputViewModel: ViewModel() {

    val dataInputComponent = DaggerMeterDataInputComponent.builder().deps(MeterDataInputDepsStore.deps).build()

}