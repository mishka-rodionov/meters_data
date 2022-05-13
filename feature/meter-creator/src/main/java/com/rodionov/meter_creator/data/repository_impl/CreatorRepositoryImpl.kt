package com.rodionov.meter_creator.data.repository_impl

import com.rodionov.database.dao.MeterDao
import com.rodionov.domain.models.Flat
import com.rodionov.meter_creator.domain.repository.CreatorRepository

class CreatorRepositoryImpl(private val meterDao: MeterDao): CreatorRepository {

    override suspend fun getFlats() {

    }

    override suspend fun createFlat(flat: Flat) {

    }
}