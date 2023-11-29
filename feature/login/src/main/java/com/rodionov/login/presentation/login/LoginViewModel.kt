package com.rodionov.login.presentation.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.login.data.dto.Credentials
import com.rodionov.login.domain.repository.LoginRepository
import com.rodionov.utils.repositories.SharedPreferencesRepository
import kotlinx.coroutines.launch
import java.security.MessageDigest

class LoginViewModel(
    private val preferencesRepository: SharedPreferencesRepository,
    private val loginRepository: LoginRepository
): BaseViewModel() {

    fun test() {
        Log.d("LOG_TAG", "test: user id = ${preferencesRepository.getUserId()}")

    }

    fun md5(password: String) {
        val digest = MessageDigest.getInstance("MD5")
        digest.digest()
    }

    fun getUser() {
        viewModelScope.launch {
            loginRepository.getUser(Credentials("", ""), {
                preferencesRepository.putUserId(it.id)
            }, {})
        }
    }

}