package com.rodionov.meter_creator.data.repository_impl

import com.rodionov.base.interaction.BaseRepository
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.entities.MeterEntity
import com.rodionov.database.mappers.toModel
import com.rodionov.domain.models.Meter
import com.rodionov.meter_creator.domain.repository.EditorRepository

class EditorRepositoryImpl(
    private val meterDao: MeterDao,
    errorHandler: ErrorHandler
): BaseRepository(errorHandler), EditorRepository {

    override suspend fun getMeters(onSuccess: (List<Meter>) -> Unit, onState: (State) -> Unit) {
        execute(onSuccess = onSuccess, onState = onState) {
            meterDao.getMeterEntities().map(MeterEntity::toModel)
        }
    }
}