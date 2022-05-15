package com.rodionov.meter_creator.presentation.start_creator

import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Flat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StartCreatorViewModel: BaseViewModel() {

    fun removeFlatListener(id: String) {}

    fun editFlatListener(id: String) {}

    suspend fun getFlats(): List<Flat> = withContext(Dispatchers.IO) {
        emptyList()
    }

}