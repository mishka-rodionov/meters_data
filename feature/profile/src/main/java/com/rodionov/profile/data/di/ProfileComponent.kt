package com.rodionov.profile.data.di

import com.rodionov.profile.presentation.profile.ProfileFragment
import dagger.Component

@Component(dependencies = [ProfileDeps::class])
interface ProfileComponent {

    fun inject(profileFragment: ProfileFragment)

    @Component.Builder
    interface Builder {

        fun deps(profileDeps: ProfileDeps): Builder

        fun build(): ProfileComponent

    }

}