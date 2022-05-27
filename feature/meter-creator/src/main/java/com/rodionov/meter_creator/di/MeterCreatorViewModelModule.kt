package com.rodionov.meter_creator.di

import androidx.lifecycle.ViewModel
import com.rodionov.meter_creator.domain.repository.CreatorRepository
import com.rodionov.meter_creator.domain.repository.EditorRepository
import com.rodionov.meter_creator.presentation.flat_creator.FlatCreatorViewModel
import com.rodionov.meter_creator.presentation.flat_editor.FlatEditorViewModel
import com.rodionov.meter_creator.presentation.meter_creator.MeterCreatorViewModel
import com.rodionov.meter_creator.presentation.start_creator.StartCreatorViewModel
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

    @IntoMap
    @ViewModelKey(StartCreatorViewModel::class)
    @Provides
    fun provideStartCreatorViewModel(creatorRepository: CreatorRepository): ViewModel = StartCreatorViewModel(creatorRepository)

    @IntoMap
    @ViewModelKey(FlatEditorViewModel::class)
    @Provides
    fun provideFlatEditorViewModel(editorRepository: EditorRepository): ViewModel = FlatEditorViewModel(editorRepository)

}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)