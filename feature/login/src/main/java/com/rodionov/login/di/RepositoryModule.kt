package com.rodionov.login.di

import com.rodionov.login.data.repository_impl.LoginRepositoryImpl
import com.rodionov.login.data.repository_impl.RegistrationRepositoryImpl
import com.rodionov.login.domain.RegistrationRepository
import com.rodionov.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository

    @Binds
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

}