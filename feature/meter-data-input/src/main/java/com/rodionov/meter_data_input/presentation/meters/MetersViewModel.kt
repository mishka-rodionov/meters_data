package com.rodionov.meter_data_input.presentation.meters

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Meter
import com.rodionov.meter_data_input.domain.repository.MeterInputRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MetersViewModel(
    private val meterInputRepository: MeterInputRepository
) : BaseViewModel() {

    private val _meters = MutableSharedFlow<List<Meter>>()
    val meters: SharedFlow<List<Meter>> = _meters.asSharedFlow()

    init {
        getMeters()
    }

    fun getMeters() {
        viewModelScope.launch {
            meterInputRepository.getMeters({ meters ->
                viewModelScope.launch {
                    _meters.emit(meters)
                }
                meters.forEach {
                    Log.d("LOG_TAG", "getMeters:  meter = $it")
                }
            }, ::handleState)
        }
    }

}