package com.rodionov.login.presentation.login

import android.util.Log
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository
import java.security.MessageDigest

class LoginViewModel(
    private val preferencesRepository: SharedPreferencesRepository
): BaseViewModel() {

    fun test() {
        Log.d("LOG_TAG", "test: user id = ${preferencesRepository.getUserId()}")

    }

    fun md5(password: String) {
        val digest = MessageDigest.getInstance("MD5")
        digest.digest()
    }

}