package com.rodionov.base.navigation

import android.os.Bundle
import androidx.navigation.NavOptions

sealed class NavigationEvent {
    data class Navigate(val bundle: Bundle): NavigationEvent()
    data class NavigateWithOptions(val bundle: Bundle, val navOptions: NavOptions): NavigationEvent()
}
