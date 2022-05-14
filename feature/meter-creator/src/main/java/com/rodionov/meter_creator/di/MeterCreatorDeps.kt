package com.rodionov.meter_creator.di

import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao

interface MeterCreatorDeps {

    val meterDao: MeterDao
    val flatDao: FlatDao

}