package com.rodionov.meter_creator.domain.repository

import com.rodionov.base.state.State
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo

interface EditorRepository {

    suspend fun getMeters(meterIds: List<String>, onSuccess: (List<Meter>) -> Unit, onState: (State) -> Unit)
    suspend fun getMeterInfo(meterId: String, onSuccess: (MeterInfo) -> Unit, onState: (State) -> Unit)
    suspend fun saveFlat(flat: Flat, onSuccess: (Boolean) -> Unit, onState: (State) -> Unit)
    suspend fun saveMeter(meter: Meter, onSuccess: (Boolean) -> Unit, onState: (State) -> Unit)

}