package com.rodionov.meter_data_input.domain.repository

import com.rodionov.base.state.State
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter

interface MeterInputRepository{

    suspend fun getMeters(onSuccess: (List<Meter>) -> Unit, onState: (State) -> Unit)
    suspend fun getFlats(onSuccess: (List<Flat>) -> Unit, onState: (State) -> Unit)
    suspend fun saveMeterData(data: Double, meterId: String, onSuccess: (String) -> Unit, onState: (State) -> Unit)

    suspend fun getLastMeterValue(meterId: String): Double

}