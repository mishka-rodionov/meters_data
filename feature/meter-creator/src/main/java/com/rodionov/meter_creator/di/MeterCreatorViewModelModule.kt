package com.rodionov.meter_creator.di

import androidx.lifecycle.ViewModel
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import com.rodionov.meter_creator.presentation.flat_creator.FlatCreatorViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
class MeterCreatorViewModelModule {

    @IntoMap
    @ViewModelKey(FlatCreatorViewModel::class)
    @Provides
    fun provideFlatCreatorViewModel(profileRepository: CreatorRepository): ViewModel = FlatCreatorViewModel(profileRepository)

}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)