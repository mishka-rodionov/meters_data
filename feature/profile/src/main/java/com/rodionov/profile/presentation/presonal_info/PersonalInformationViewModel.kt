package com.rodionov.profile.presentation.presonal_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodionov.domain.models.User
import com.rodionov.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalInformationViewModel(
    private val profileRepository: ProfileRepository
): ViewModel() {

    fun setUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.setPersonalInformation(user)
        }
    }

}