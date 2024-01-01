package com.rodionov.login.presentation.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.login.data.dto.Credentials
import com.rodionov.login.domain.repository.LoginRepository
import com.rodionov.utils.repositories.SharedPreferencesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest

class LoginViewModel(
    private val preferencesRepository: SharedPreferencesRepository,
    private val loginRepository: LoginRepository
) : BaseViewModel() {

    private val _login = MutableSharedFlow<Boolean>()
    val login: SharedFlow<Boolean> = _login.asSharedFlow()

    fun test() {
        Log.d("LOG_TAG", "test: user id = ${preferencesRepository.getUserId()}")

    }

    fun md5(password: String) {
        val digest = MessageDigest.getInstance("MD5")
        digest.digest()
    }

    fun getUser(login: String, password: String) {
        viewModelScope.launch {
            loginRepository.getUser(Credentials(login, password), { user ->
                viewModelScope.launch {
                    _login.emit(
                        if (user != null) {
                            preferencesRepository.putUserId(user.id)
                            true
                        } else {
                            false
                        }
                    )
                }

            }) {}
        }
    }

}