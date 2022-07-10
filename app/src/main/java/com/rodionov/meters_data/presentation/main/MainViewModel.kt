package com.rodionov.meters_data.presentation.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val preferencesRepository: SharedPreferencesRepository
) : BaseViewModel() {

    private val _toLogin = MutableSharedFlow<Boolean>()
    val toLogin: SharedFlow<Boolean> = _toLogin.asSharedFlow()

    private val _test = MutableSharedFlow<Boolean>()
    val test: SharedFlow<Boolean> = _test.asSharedFlow()

    fun checkFirstStart() {
        preferencesRepository.putUserId("123")
        Log.d("LOG_TAG", "checkFirstStart: ")
        val flag = preferencesRepository.getUserId() != null
        Log.d("LOG_TAG", "checkFirstStart: $flag")
        viewModelScope.launch(Dispatchers.IO) { _toLogin.emit(flag) }
    }

    fun test() {
        Log.d("LOG_TAG", "test: ")
        viewModelScope.launch(Dispatchers.IO) { _test.emit(false) }
    }

}