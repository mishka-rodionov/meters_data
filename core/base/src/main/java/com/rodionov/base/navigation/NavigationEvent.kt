package com.rodionov.base.navigation

import android.os.Bundle
import androidx.navigation.NavOptions

sealed class NavigationEvent {
    data class Navigate(
        val destination: Int,
        val bundle: Bundle? = null,
        val navOptions: NavOptions? = null
    ) : NavigationEvent()

    data class Back(
        val destination: Int? = null,
        val inclusive: Boolean? = null,
        val saveState: Boolean? = null
    ) : NavigationEvent()
}
