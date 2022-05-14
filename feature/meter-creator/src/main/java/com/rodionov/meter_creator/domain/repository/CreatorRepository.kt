package com.rodionov.meter_creator.domain.repository

import com.rodionov.base.state.State
import com.rodionov.domain.models.Flat

interface CreatorRepository {

    suspend fun getFlats()
    suspend fun createFlat(flat: Flat, onSuccess: (Unit) -> Unit, onState: (State) -> Unit)

}