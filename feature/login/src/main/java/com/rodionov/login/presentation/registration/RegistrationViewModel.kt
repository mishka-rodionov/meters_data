package com.rodionov.login.presentation.registration

import android.util.Log
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository

class RegistrationViewModel(
    private val preferencesRepository: SharedPreferencesRepository
): BaseViewModel() {

    fun test() {
        Log.d("LOG_TAG", "test: registration view model user id ${preferencesRepository.getUserId()}")
    }

}