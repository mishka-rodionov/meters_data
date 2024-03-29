package com.rodionov.profile.data.di

import androidx.lifecycle.ViewModel
import com.rodionov.database.dao.UserDao
import com.rodionov.profile.data.repository_impl.ProfileRepositoryImpl
import com.rodionov.profile.domain.repository.ProfileRepository
import com.rodionov.profile.presentation.presonal_info.PersonalInformationViewModel
import com.rodionov.profile.presentation.profile.ProfileViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository
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
    fun provideProfileViewModel(
        profileRepository: ProfileRepository,
        sharedPreferencesRepository: SharedPreferencesRepository
    ): ViewModel = ProfileViewModel(profileRepository, sharedPreferencesRepository)

    @IntoMap
    @ViewModelKey(PersonalInformationViewModel::class)
    @Provides
    fun providePersonalInformationViewModel(profileRepository: ProfileRepository): ViewModel =
        PersonalInformationViewModel(profileRepository)

    @Provides
    fun provideProfileRepository(
        userDao: UserDao,
        preferencesRepository: SharedPreferencesRepository
    ): ProfileRepository =
        ProfileRepositoryImpl(userDao, preferencesRepository)

}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)