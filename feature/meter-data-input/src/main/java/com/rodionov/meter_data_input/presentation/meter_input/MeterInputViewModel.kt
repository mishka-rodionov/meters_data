package com.rodionov.meter_data_input.presentation.meter_input

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_data_input.domain.repository.MeterInputRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MeterInputViewModel(
    private val meterInputRepository: MeterInputRepository
) : BaseViewModel() {

    private val _meterInputEvents = MutableSharedFlow<String>()
    val meterInputEvents = _meterInputEvents.asSharedFlow()

    fun saveData(meterData: Double, meterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            meterInputRepository.saveMeterData(data = meterData, meterId = meterId, onSuccess = {
                Log.d("LOG_TAG", "saveData: $it")
                viewModelScope.launch { _meterInputEvents.emit(it) }
            }, onState = ::handleState)
        }
    }

}