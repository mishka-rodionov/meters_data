package com.rodionov.meter_creator.presentation.meter_creator

import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo
import com.rodionov.domain.models.MeterType
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeterCreatorViewModel(
    private val creatorRepository: CreatorRepository
) : BaseViewModel() {

    fun createAndSaveMeter(
        flatId: String,
        meterType: MeterType,
        serialNumber: String,
        meterName: String,
        dataSendDay: Int,
        payRate: Double,
        dataSendType: String,
        company: String
    ) {
        val meter = Meter(
            name = meterName,
            serialNumber = serialNumber,
            meterUnit = meterType.units,
            type = meterType,
        )
        val meterInfo = MeterInfo(
            meterId = meter.id,
            dataSendDay = dataSendDay,
            payRate = payRate,
            dataSendType = dataSendType,
            company = company
        )
        viewModelScope.launch(Dispatchers.IO) {
            creatorRepository.createMeter(
                meter = meter,
                { createAndSaveMeterInfo(meterInfo = meterInfo) },
                ::handleState
            )
            creatorRepository.addMeterToFlat(
                flatId = flatId,
                meterId = meter.id,
                onSuccess = {},
                onState = ::handleState
            )
        }
    }

    private fun createAndSaveMeterInfo(meterInfo: MeterInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            creatorRepository.createMeterInfo(
                meterInfo = meterInfo,
                {},
                ::handleState
            )
        }
    }


}