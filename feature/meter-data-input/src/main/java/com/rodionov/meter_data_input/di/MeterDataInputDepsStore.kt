package com.rodionov.meter_data_input.di

import kotlin.properties.Delegates

object MeterDataInputDepsStore {

    var deps: MeterDataInputDeps by Delegates.notNull()

}