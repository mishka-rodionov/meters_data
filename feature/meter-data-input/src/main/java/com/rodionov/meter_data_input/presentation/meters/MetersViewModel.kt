package com.rodionov.meter_data_input.presentation.meters

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.Meter
import com.rodionov.meter_data_input.data.dto.MeterItem
import com.rodionov.meter_data_input.domain.repository.MeterInputRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MetersViewModel(
    private val meterInputRepository: MeterInputRepository
) : BaseViewModel() {

    private val meters = mutableListOf<Meter>()

    private val _metersFlow = MutableSharedFlow<List<MeterItem>>()
    val metersFlow: SharedFlow<List<MeterItem>> = _metersFlow.asSharedFlow()


    fun getMeters() {
        viewModelScope.launch {
            meterInputRepository.getMeters(::handleGetMeters, ::handleState)
        }
    }

    private fun handleGetMeters(meters: List<Meter>) {
        this.meters.addAll(meters)
        viewModelScope.launch {
            meterInputRepository.getFlats(::handleGetFlats, ::handleState)
        }

//        viewModelScope.launch {
//            _metersFlow.emit(meters)
//        }
        meters.forEach {
            Log.d("LOG_TAG", "getMeters:  meter = $it")
        }
    }

    private fun handleGetFlats(flats: List<Flat>) {
        val meterItems = mutableListOf<MeterItem>()
        meters.forEach { meter ->
            val flat = flats.find { it.meters?.contains(meter) ?: false }
            if (flat != null) {
                meterItems.add(
                    MeterItem(
                        meterType = meter.type,
                        name = meter.name,
                        address = flat.address,
                        lastData = "0"
                    )
                )
            }
        }
        viewModelScope.launch { _metersFlow.emit(meterItems) }
    }

}