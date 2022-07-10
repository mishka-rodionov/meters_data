package com.rodionov.profile.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.User
import com.rodionov.profile.domain.repository.ProfileRepository
import com.rodionov.utils.repositories.SharedPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : BaseViewModel() {

    private val _user = MutableSharedFlow<User>()
    val user: SharedFlow<User> = _user.asSharedFlow()

    private val _exit = MutableSharedFlow<Boolean>()
    val exit: SharedFlow<Boolean> = _exit.asSharedFlow()

    fun getDefaultUser() {
        Log.d("LOG_TAG", "getDefaultUser: ")
        viewModelScope.launch(Dispatchers.IO) { _user.emit(profileRepository.getUser())}
    }

    fun exit() {
        viewModelScope.launch(Dispatchers.IO) {
            sharedPreferencesRepository.putUserId("")
            _exit.emit(true)
        }
    }

}