package com.rodionov.meter_creator.domain.repository

import com.rodionov.domain.models.Flat

interface CreatorRepository {

    suspend fun getFlats()
    suspend fun createFlat(flat: Flat)

}