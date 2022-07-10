package com.rodionov.base.factory

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class CommonViewModelKey(val value: KClass<out ViewModel>)
