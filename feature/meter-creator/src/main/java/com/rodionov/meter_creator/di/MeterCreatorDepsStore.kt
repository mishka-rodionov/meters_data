package com.rodionov.meter_creator.di

import kotlin.properties.Delegates

object MeterCreatorDepsStore {

    var deps: MeterCreatorDeps by Delegates.notNull()

}