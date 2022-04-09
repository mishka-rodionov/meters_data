package com.rodionov.profile.data.di

import com.rodionov.profile.data.factory.ProfileViewModelFactory
import com.rodionov.profile.presentation.profile.ProfileFragment
import dagger.Component

@Component(dependencies = [ProfileDeps::class], modules = [ViewModelModule::class])
interface ProfileComponent {

    fun inject(profileFragment: ProfileFragment)

//    fun getViewModelFactory(): ProfileViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(profileDeps: ProfileDeps): Builder

        fun build(): ProfileComponent

    }

}