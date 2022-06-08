package com.rodionov.meter_creator.domain.repository

import com.rodionov.base.state.State
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo

interface CreatorRepository {

    suspend fun getFlats(onSuccess: (List<Flat>) -> Unit, onState: (State) -> Unit)
    suspend fun createFlat(flat: Flat, onSuccess: (Flat) -> Unit, onState: (State) -> Unit)
    suspend fun addMeterToFlat(flatId:String, meterId: String, onSuccess: (Meter) -> Unit, onState: (State) -> Unit)
    suspend fun createMeter(meter: Meter, onSuccess: (Meter) -> Unit, onState: (State) -> Unit)
    suspend fun createMeterInfo(meterInfo: MeterInfo, onSuccess: (MeterInfo) -> Unit, onState: (State) -> Unit)

}