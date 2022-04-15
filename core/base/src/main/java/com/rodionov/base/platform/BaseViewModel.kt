package com.rodionov.base.platform

import androidx.lifecycle.ViewModel
import com.rodionov.base.navigation.NavigationEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class BaseViewModel: ViewModel() {

    private val _navigate = MutableSharedFlow<NavigationEvent>()
    val navigate: SharedFlow<NavigationEvent> = _navigate.asSharedFlow()

}