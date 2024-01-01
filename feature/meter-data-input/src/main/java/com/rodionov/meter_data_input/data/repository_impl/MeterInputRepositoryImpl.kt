package com.rodionov.meter_data_input.data.repository_impl

import com.rodionov.base.interaction.BaseRepository
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.dao.MeterDataDao
import com.rodionov.database.entities.FlatEntity
import com.rodionov.database.entities.MeterDataEntity
import com.rodionov.database.entities.MeterEntity
import com.rodionov.database.mappers.toModel
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.meter_data_input.domain.repository.MeterInputRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class MeterInputRepositoryImpl @Inject constructor(
    private val meterDao: MeterDao,
    private val flatDao: FlatDao,
    private val meterDataDao: MeterDataDao,
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

    override suspend fun saveMeterData(
        data: Double,
        meterId: String,
        onSuccess: (String) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess, onState) {
            meterDataDao.saveMeterData(MeterDataEntity(
                id = UUID.randomUUID().toString(),
                meterId = meterId,
                dateTime = Date(),
                meterValue = data
            ))
            return@execute meterId
        }
    }

    override suspend fun getLastMeterValue(meterId: String) = meterDataDao.getLastMeterData(meterId)
}