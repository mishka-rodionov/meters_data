package com.rodionov.meter_creator.presentation.flat_editor

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Meter
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import com.rodionov.meter_creator.domain.repository.EditorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FlatEditorViewModel(
    private val editorRepository: EditorRepository
): BaseViewModel() {

    private val _meters = MutableSharedFlow<List<Meter>>()
    val meters: SharedFlow<List<Meter>> = _meters.asSharedFlow()

    fun getMeters(meterIds: List<String>) {
        Log.d("LOG_TAG", "getMeters: ")
        viewModelScope.launch(Dispatchers.IO) {
            editorRepository.getMeters(meterIds = meterIds, onSuccess = ::handleGetMeters, onState = ::handleState)
        }
    }

    private fun handleGetMeters(meters: List<Meter>) {
        meters.forEach { Log.d("LOG_TAG", "handleGetMeters: name = ${it.name}") }
        viewModelScope.launch(Dispatchers.IO){ _meters.emit(meters) }
    }

}