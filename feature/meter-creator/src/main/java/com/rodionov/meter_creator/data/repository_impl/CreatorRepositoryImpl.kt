package com.rodionov.meter_creator.data.repository_impl

import com.rodionov.base.interaction.BaseRepository
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import com.rodionov.database.dao.FlatDao
import com.rodionov.database.dao.MeterDao
import com.rodionov.database.mappers.toEntity
import com.rodionov.domain.models.Flat
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreatorRepositoryImpl(
    private val meterDao: MeterDao,
    private val flatDao: FlatDao,
    errorHandler: ErrorHandler
) : BaseRepository(errorHandler), CreatorRepository {

    override suspend fun getFlats() {

    }

    override suspend fun createFlat(flat: Flat,  onSuccess: (Unit) -> Unit, onState: (State) -> Unit) {
        execute(onSuccess = onSuccess, onState = onState) {
            flatDao.setFlatEntity(flat.toEntity())
        }
    }
}