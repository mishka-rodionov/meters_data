package com.rodionov.meter_creator.domain.repository

import com.rodionov.base.state.State
import com.rodionov.domain.models.Meter

interface EditorRepository {

    suspend fun getMeters(onSuccess: (List<Meter>) -> Unit, onState: (State) -> Unit)

}