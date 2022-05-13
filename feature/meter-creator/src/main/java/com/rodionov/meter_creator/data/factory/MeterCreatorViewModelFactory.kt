package com.rodionov.meter_creator.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class MeterCreatorViewModelFactory @Inject constructor(
    private val viewModelProviders: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T
            ?: throw IllegalArgumentException("Unknown ViewModel class")
    }
}