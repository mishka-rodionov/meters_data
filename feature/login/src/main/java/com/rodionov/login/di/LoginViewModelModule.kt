package com.rodionov.login.di

import androidx.lifecycle.ViewModel
import com.rodionov.base.factory.CommonViewModelKey
import com.rodionov.login.presentation.login.LoginViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginViewModelModule {

    @IntoMap
    @CommonViewModelKey(LoginViewModel::class)
    @Provides
    fun provideLoginViewModel(preferencesRepository: SharedPreferencesRepository): ViewModel = LoginViewModel(preferencesRepository)

}