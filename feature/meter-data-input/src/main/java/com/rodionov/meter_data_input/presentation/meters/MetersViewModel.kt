package com.rodionov.meter_data_input.presentation.meters

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_data_input.domain.repository.MeterInputRepository
import kotlinx.coroutines.launch

class MetersViewModel(
    private val meterInputRepository: MeterInputRepository
): BaseViewModel() {

    fun getMeters() {
        viewModelScope.launch {
            meterInputRepository.getMeters( { meters ->

                meters.forEach {
                    Log.d("LOG_TAG", "getMeters:  meter = $it")
                }
            }, ::handleState)
        }
    }

}