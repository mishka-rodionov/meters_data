package com.rodionov.login.di

import com.rodionov.login.presentation.login.LoginFragment
import com.rodionov.login.presentation.registration.RegistrationFragment
import dagger.Component

@Component(dependencies = [LoginDeps::class], modules = [LoginViewModelModule::class])
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)
    fun inject(registrationFragment: RegistrationFragment)

    @Component.Builder
    interface Builder {

        fun deps(loginDeps: LoginDeps): Builder

        fun build(): LoginComponent

    }

}