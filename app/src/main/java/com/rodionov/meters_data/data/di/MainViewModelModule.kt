package com.rodionov.meters_data.data.di

import androidx.lifecycle.ViewModel
import com.rodionov.meters_data.presentation.main.MainViewModel
import com.rodionov.utils.repositories.SharedPreferencesRepository
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
class MainViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Provides
    fun provideMainViewModel(sharedPreferencesRepository: SharedPreferencesRepository) = MainViewModel(sharedPreferencesRepository)

}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)