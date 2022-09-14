package com.rodionov.meter_data_input.data.repository_impl

import com.rodionov.base.interaction.BaseRepository
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.entities.FlatEntity
import com.rodionov.database.entities.MeterEntity
import com.rodionov.database.mappers.toModel
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.meter_data_input.domain.repository.MeterInputRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MeterInputRepositoryImpl @Inject constructor(
    private val meterDao: MeterDao,
    private val flatDao: FlatDao,
    errorHandler: ErrorHandler
) : BaseRepository(errorHandler),
    MeterInputRepository {

    override suspend fun getMeters(onSuccess: (List<Meter>) -> Unit, onState: (State) -> Unit) {
        execute(onSuccess, onState) { meterDao.getMeterEntities().map(MeterEntity::toModel) }
    }

    override suspend fun getFlats(onSuccess: (List<Flat>) -> Unit, onState: (State) -> Unit) {
        execute(onSuccess, onState) {
            flatDao.getFlats().map {
                it.toModel { ids ->
                    withContext(Dispatchers.IO) {
                        meterDao.getMeterEntities(ids ?: emptyList()).map(MeterEntity::toModel)
                    }
                }
            }
        }
    }
}