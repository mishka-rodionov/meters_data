package com.rodionov.login.di

import com.rodionov.login.presentation.login.LoginFragment
import dagger.Component

@Component(dependencies = [LoginDeps::class])
interface LoginComponent {

    fun inject(loginFragment: LoginFragment)

    @Component.Builder
    interface Builder {

        fun deps(loginDeps: LoginDeps): Builder

        fun build(): LoginComponent

    }

}