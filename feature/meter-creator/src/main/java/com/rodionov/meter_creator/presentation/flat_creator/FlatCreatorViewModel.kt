package com.rodionov.meter_creator.presentation.flat_creator

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Flat
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FlatCreatorViewModel(
    private val creatorRepository: CreatorRepository
): BaseViewModel() {

    private val _flatCreated = MutableSharedFlow<String>()
    val flatCreated: SharedFlow<String> = _flatCreated.asSharedFlow()

    fun createFlat(name: String, address: String) {
        viewModelScope.launch { creatorRepository.createFlat(Flat(name = name, address = address, ), { flat ->
            Log.d("LOG_TAG", "createFlat: operation is finished")
            viewModelScope.launch { _flatCreated.emit(flat.id) }
        }, ::handleState) }
    }

}