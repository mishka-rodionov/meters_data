package com.rodionov.meter_creator.presentation.flat_creator

import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Flat
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import kotlinx.coroutines.launch

class FlatCreatorViewModel(
    private val creatorRepository: CreatorRepository
): BaseViewModel() {

    fun createFlat(name: String, address: String) {
        viewModelScope.launch { creatorRepository.createFlat(Flat(name = name, address = address, )) }
    }

}