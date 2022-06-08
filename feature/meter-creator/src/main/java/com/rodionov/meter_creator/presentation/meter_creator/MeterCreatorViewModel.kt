package com.rodionov.meter_creator.presentation.meter_creator

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo
import com.rodionov.domain.models.MeterType
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MeterCreatorViewModel(
    private val creatorRepository: CreatorRepository
) : BaseViewModel() {

    private val _finish = MutableSharedFlow<Meter>()
    val finish: SharedFlow<Meter> = _finish.asSharedFlow()

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
                { createAndSaveMeterInfo(meterInfo = meterInfo, flatId = flatId, meterId = meter.id) },
                ::handleState
            )

        }
    }

    private fun addMeterToFlat(flatId: String, meterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            creatorRepository.addMeterToFlat(
                flatId = flatId,
                meterId = meterId,
                onSuccess = ::handleFinish,
                onState = ::handleState
            )
        }
    }

    private fun createAndSaveMeterInfo(meterInfo: MeterInfo, flatId: String, meterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            creatorRepository.createMeterInfo(
                meterInfo = meterInfo,
                {
                    Log.d("LOG_TAG", "createAndSaveMeterInfo: ")
                    addMeterToFlat(flatId, meterId) },
                ::handleState
            )
        }
    }

    private fun handleFinish(meter: Meter) {
        viewModelScope.launch {
            _finish.emit(meter)
        }
    }


}