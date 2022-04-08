package com.rodionov.profile.data.di

import androidx.lifecycle.ViewModel
import com.rodionov.profile.domain.repository.ProfileRepository
import com.rodionov.profile.presentation.profile.ProfileViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
class ViewModelModule {

    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    @Provides
    fun provideProfileViewModel(profileRepository: ProfileRepository) = ProfileViewModel(profileRepository)

}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)