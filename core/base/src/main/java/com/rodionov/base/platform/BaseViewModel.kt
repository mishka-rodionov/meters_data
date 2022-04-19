package com.rodionov.base.platform

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import com.rodionov.base.navigation.NavigationEvent
import com.rodionov.base.state.State
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {

    private val _navigate = MutableSharedFlow<NavigationEvent>()
    val navigate: SharedFlow<NavigationEvent> = _navigate.asSharedFlow()

    private val _state = MutableSharedFlow<State>()
    val state: SharedFlow<State> = _state.asSharedFlow()

    fun navigate(destination: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
        viewModelScope.launch {
            _navigate.emit(NavigationEvent.Navigate(
                destination, bundle, navOptions
            ))
        }
    }

    fun handleState(state: State) {
        viewModelScope.launch { _state.emit(state) }
    }

}