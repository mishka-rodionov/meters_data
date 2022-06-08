package com.rodionov.meter_creator.presentation.start_creator

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Flat
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StartCreatorViewModel(private val creatorRepository: CreatorRepository) : BaseViewModel() {

    private val _flats = MutableSharedFlow<List<Flat>?>()
    val flats: SharedFlow<List<Flat>?> = _flats.asSharedFlow()

    fun removeFlatListener(id: String) {
        Log.d("LOG_TAG", "removeFlatListener: ")
    }

    fun editFlatListener(id: String) {
        Log.d("LOG_TAG", "editFlatListener: ")
    }

    fun getFlats() {
        viewModelScope.launch(Dispatchers.IO) {
            creatorRepository.getFlats(onSuccess = {
                viewModelScope.launch {
                    _flats.emit(it)
                }
            }, onState = ::handleState)
        }
    }

}