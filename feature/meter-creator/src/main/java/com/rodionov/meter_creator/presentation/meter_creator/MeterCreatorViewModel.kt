package com.rodionov.meter_creator.presentation.meter_creator

import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.domain.repository.CreatorRepository

class MeterCreatorViewModel(
    private val creatorRepository: CreatorRepository
): BaseViewModel() {
}