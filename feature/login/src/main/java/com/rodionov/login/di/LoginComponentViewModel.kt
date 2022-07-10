package com.rodionov.login.di

import androidx.lifecycle.ViewModel

class LoginComponentViewModel: ViewModel() {

    val loginComponent: LoginComponent = DaggerLoginComponent.builder().deps(LoginDepsStore.deps).build()

}