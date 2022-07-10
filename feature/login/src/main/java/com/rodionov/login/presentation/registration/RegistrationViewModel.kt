package com.rodionov.login.presentation.registration

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.User
import com.rodionov.login.domain.RegistrationRepository
import com.rodionov.utils.repositories.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*

class RegistrationViewModel(
    private val preferencesRepository: SharedPreferencesRepository,
    private val registrationRepository: RegistrationRepository
) : BaseViewModel() {

    private val _user = MutableSharedFlow<User>()
    val user: SharedFlow<User> = _user.asSharedFlow()

    fun test() {
        Log.d(
            "LOG_TAG",
            "test: registration view model user id ${preferencesRepository.getUserId()}"
        )
    }

    fun createNewUser(login: String, firstName: String, lastName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registrationRepository.createNewUser(
                user = User(
                    id = UUID.randomUUID().toString(),
                    login = login,
                    firstName = firstName,
                    lastName = lastName,
                    email = "",
                    phone = null,
                    flatsId = null
                ),
                onSuccess = ::handleUserCreate,
                onState = ::handleState
            )
        }
    }

    private fun handleUserCreate(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.putUserId(user.id)
            _user.emit(user)
        }
    }

}