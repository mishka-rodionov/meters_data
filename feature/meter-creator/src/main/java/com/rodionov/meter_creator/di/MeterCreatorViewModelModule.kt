package com.rodionov.meter_creator.di

import androidx.lifecycle.ViewModel
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import com.rodionov.meter_creator.presentation.flat_creator.FlatCreatorViewModel
import com.rodionov.meter_creator.presentation.meter_creator.MeterCreatorViewModel
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
    fun provideFlatCreatorViewModel(creatorRepository: CreatorRepository): ViewModel = FlatCreatorViewModel(creatorRepository)

    @IntoMap
    @ViewModelKey(MeterCreatorViewModel::class)
    @Provides
    fun provideMeterCreatorViewModel(creatorRepository: CreatorRepository): ViewModel = MeterCreatorViewModel(creatorRepository)

}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)