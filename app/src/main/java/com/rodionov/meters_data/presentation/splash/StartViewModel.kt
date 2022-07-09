package com.rodionov.meters_data.presentation.splash

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StartViewModel(
    private val preferences: SharedPreferencesRepository
): BaseViewModel() {

    private val _toLogin = MutableSharedFlow<Boolean>()
    val toLogin: SharedFlow<Boolean> = _toLogin.asSharedFlow()

    fun checkFirstStart() {
        preferences.putUserId("123")
        Log.d("LOG_TAG", "checkFirstStart: ")
        val flag = preferences.getUserId() != null
        Log.d("LOG_TAG", "checkFirstStart: $flag")
        viewModelScope.launch(Dispatchers.IO) { _toLogin.emit(flag) }
    }

}