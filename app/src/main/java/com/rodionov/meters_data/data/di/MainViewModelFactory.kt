package com.rodionov.meters_data.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

//class MainViewModelFactory @Inject constructor(
//    private val viewModelProviders:  @JvmSuppressWildcards MutableMap<Class<out ViewModel>, Provider<ViewModel>>
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return viewModelProviders[modelClass]?.get() as T
//            ?: throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}