package com.rodionov.meter_creator.di

import com.rodionov.base.state.ErrorHandler
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao

interface MeterCreatorDeps {

    val meterDao: MeterDao
    val flatDao: FlatDao
    val errorHandler: ErrorHandler

}