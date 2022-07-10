package com.rodionov.login.presentation.login

import android.util.Log
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository

class LoginViewModel(
    private val preferencesRepository: SharedPreferencesRepository
): BaseViewModel() {

    fun test() {
        Log.d("LOG_TAG", "test: user id = ${preferencesRepository.getUserId()}")

    }

}