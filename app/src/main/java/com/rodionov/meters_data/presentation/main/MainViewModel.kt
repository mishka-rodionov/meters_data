package com.rodionov.meters_data.presentation.main

import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val preferencesRepository: SharedPreferencesRepository
) : BaseViewModel() {

    private val _toLogin = MutableSharedFlow<Boolean>()
    val toLogin: SharedFlow<Boolean> = _toLogin.asSharedFlow()

    fun checkFirstStart() {
        viewModelScope.launch { _toLogin.emit(preferencesRepository.getUserId() == null) }
    }

}