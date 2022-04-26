package com.rodionov.profile.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rodionov.profile.domain.repository.ProfileRepository
import com.rodionov.profile.presentation.presonal_info.PersonalInformationViewModel
import com.rodionov.profile.presentation.profile.ProfileViewModel
import javax.inject.Inject
import javax.inject.Provider

class ProfileViewModelFactory @Inject constructor(
    val viewModelProviders: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
//    val profileRepository: ProfileRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return when (modelClass) {
//            ProfileViewModel::class.java -> ProfileViewModel(profileRepository)
//            PersonalInformationViewModel::class.java -> PersonalInformationViewModel(profileRepository)
//            else -> throw IllegalArgumentException("Unknown ViewModel class")
//        } as T
        return viewModelProviders[modelClass]?.get() as T
            ?: throw IllegalArgumentException("Unknown ViewModel class")
    }
}