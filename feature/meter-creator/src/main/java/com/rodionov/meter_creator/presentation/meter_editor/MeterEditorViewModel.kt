package com.rodionov.meter_creator.presentation.meter_editor

import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo
import com.rodionov.meter_creator.domain.repository.EditorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MeterEditorViewModel(
    private val editorRepository: EditorRepository
) : BaseViewModel() {

    private val _meterInfo = MutableSharedFlow<MeterInfo>()
    val meterInfo: SharedFlow<MeterInfo> = _meterInfo.asSharedFlow()

    private val _isSaveMeterInfo = MutableSharedFlow<Boolean>()
    val isSaveMeterInfo: SharedFlow<Boolean> = _isSaveMeterInfo.asSharedFlow()

    fun getMeterInfo(meterId: String) {
        viewModelScope.launch {
            editorRepository.getMeterInfo(
                meterId = meterId,
                onSuccess = ::handleMeterInfo,
                onState = ::handleState
            )
        }
    }

    private fun handleMeterInfo(meterInfo: MeterInfo) {
        viewModelScope.launch { _meterInfo.emit(meterInfo) }
    }

    fun saveMeter(meter: Meter, meterInfo: MeterInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            editorRepository.saveMeter(meter = meter, onSuccess = {
                viewModelScope.launch(Dispatchers.IO) {
                    saveMeterInfo(meterInfo)
                }
            }, onState = ::handleState)
        }
    }

    fun saveMeterInfo(meterInfo: MeterInfo) {
        viewModelScope.launch(Dispatchers.IO){
            editorRepository.saveMeterInfo(meterInfo = meterInfo, onSuccess = ::handleMeterInfoSave , onState = ::handleState)
        }
    }

    private fun handleMeterInfoSave(isSave: Boolean) {
        viewModelScope.launch { _isSaveMeterInfo.emit(isSave) }
    }

}