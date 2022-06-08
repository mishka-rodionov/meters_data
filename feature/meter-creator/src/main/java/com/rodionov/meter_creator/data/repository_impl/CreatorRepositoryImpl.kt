package com.rodionov.meter_creator.data.repository_impl

import android.util.Log
import com.rodionov.base.interaction.BaseRepository
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.dao.MeterInfoDao
import com.rodionov.database.entities.FlatEntity
import com.rodionov.database.entities.MeterEntity
import com.rodionov.database.mappers.toEntity
import com.rodionov.database.mappers.toModel
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreatorRepositoryImpl(
    private val meterDao: MeterDao,
    private val flatDao: FlatDao,
    private val meterInfoDao: MeterInfoDao,
    errorHandler: ErrorHandler
) : BaseRepository(errorHandler), CreatorRepository {

    override suspend fun getFlats(onSuccess: (List<Flat>) -> Unit, onState: (State) -> Unit) {
        execute(onSuccess = onSuccess, onState = onState) {
//            throw Throwable(message = "Test exception!")
            flatDao.getFlats().map {
                it.toModel { ids ->
                    withContext(Dispatchers.IO) {
                        meterDao.getMeterEntities(ids ?: emptyList()).map(MeterEntity::toModel)
                    }
                }
            }
        }
    }

    override suspend fun addMeterToFlat(
        flatId: String,
        meterId: String,
        onSuccess: (Meter) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess = onSuccess, onState = onState) {
            try {
                val flat = flatDao.getFlat(flatId)

                if (flat.meters == null) {
                    flat.meters = mutableListOf(meterId)
                } else {
                   val list = mutableListOf<String>()
                    list.addAll(flat.meters ?: emptyList())
                    list.add(meterId)
                    flat.meters = list
                }
                flatDao.setFlatEntity(flat)
            } catch (e: Exception) {
                Log.d("LOG_TAG", "addMeterToFlat: ${e.message}")
                e.stackTrace.forEach {
                    Log.d("LOG_TAG", "addMeterToFlat: $it")
                }
            }
            meterDao.getMeterEntity(meterId).toModel()
        }
    }

    override suspend fun createFlat(
        flat: Flat,
        onSuccess: (Flat) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess = onSuccess, onState = onState) {
            flatDao.setFlatEntity(flat.toEntity())
            flat
        }
    }

    override suspend fun createMeter(
        meter: Meter,
        onSuccess: (Meter) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess = onSuccess, onState = onState) {
            meterDao.setMeterEntity(meterEntity = meter.toEntity())
            meter // TODO think about change return value to real value from DB
        }
    }

    override suspend fun createMeterInfo(
        meterInfo: MeterInfo,
        onSuccess: (MeterInfo) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess = onSuccess, onState = onState) {
            meterInfoDao.setMeterInfoEntity(meterInfoEntity = meterInfo.toEntity())
            meterInfo // TODO think about change return value to real value from DB
        }
    }
}