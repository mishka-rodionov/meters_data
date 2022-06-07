package com.rodionov.meter_creator.data.repository_impl

import android.util.Log
import com.rodionov.base.interaction.BaseRepository
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.dao.MeterInfoDao
import com.rodionov.database.entities.MeterEntity
import com.rodionov.database.mappers.toEntity
import com.rodionov.database.mappers.toModel
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo
import com.rodionov.meter_creator.domain.repository.EditorRepository

class EditorRepositoryImpl(
    private val meterDao: MeterDao,
    private val meterInfoDao: MeterInfoDao,
    errorHandler: ErrorHandler
): BaseRepository(errorHandler), EditorRepository {

    override suspend fun getMeters(meterIds: List<String>, onSuccess: (List<Meter>) -> Unit, onState: (State) -> Unit) {
        execute(onSuccess = onSuccess, onState = onState) {
            Log.d("LOG_TAG", "getMeters: flat id = ${meterIds.size}")
            meterDao.getMeterEntities(meterIds).map(MeterEntity::toModel)
        }
    }

    override suspend fun getMeterInfo(
        meterId: String,
        onSuccess: (MeterInfo) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess, onState) {
            Log.d("LOG_TAG", "getMeterInfo: ")
            meterInfoDao.getMeterInfoEntity(meterId).toModel()
        }
    }

    override suspend fun saveFlat(
        flat: Flat,
        onSuccess: (Boolean) -> Unit,
        onState: (State) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun saveMeter(
        meter: Meter,
        onSuccess: (Boolean) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess = onSuccess, onState = onState) {
            meterDao.setMeterEntity(meter.toEntity())
            true
        }
    }

    override suspend fun saveMeterInfo(
        meterInfo: MeterInfo,
        onSuccess: (Boolean) -> Unit,
        onState: (State) -> Unit
    ) {
        execute(onSuccess = onSuccess, onState = onState) {
            meterInfoDao.setMeterInfoEntity(meterInfo.toEntity())
            true
        }
    }
}