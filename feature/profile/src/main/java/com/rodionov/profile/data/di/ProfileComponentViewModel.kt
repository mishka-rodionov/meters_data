package com.rodionov.profile.data.di

import androidx.lifecycle.ViewModel

internal class ProfileComponentViewModel: ViewModel() {

    val profileComponent = DaggerProfileComponent.builder().deps(ProfileDepsStore.deps).build()

}