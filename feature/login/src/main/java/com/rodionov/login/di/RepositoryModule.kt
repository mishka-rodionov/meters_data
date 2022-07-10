package com.rodionov.login.di

import com.rodionov.login.data.repository_impl.RegistrationRepositoryImpl
import com.rodionov.login.domain.RegistrationRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository

}