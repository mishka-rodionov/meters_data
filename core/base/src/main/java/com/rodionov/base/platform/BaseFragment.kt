package com.rodionov.base.platform

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rodionov.base.navigation.NavigationEvent
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import kotlinx.coroutines.flow.onEach
import kotlin.properties.Delegates

open class BaseFragment(@LayoutRes val layout: Int) : Fragment(layout) {

    open val screenViewModel: BaseViewModel by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        screenViewModel.navigate.onEach(::handleNavigationEvent).launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    private fun handleNavigationEvent(navigationEvent: NavigationEvent) {
        when (navigationEvent) {
            is NavigationEvent.Navigate -> {
                findNavController().navigate(
                    resId = navigationEvent.destination,
                    args = navigationEvent.bundle,
                    navOptions = navigationEvent.navOptions
                )
            }
            is NavigationEvent.Back -> {
                when {
                    navigationEvent.destination != null && navigationEvent.inclusive != null && navigationEvent.saveState != null -> {
                        findNavController().popBackStack(
                            destinationId = navigationEvent.destination,
                            inclusive = navigationEvent.inclusive,
                            saveState = navigationEvent.saveState
                        )
                    }
                    navigationEvent.destination != null && navigationEvent.inclusive != null && navigationEvent.saveState == null -> {
                        findNavController().popBackStack(
                            destinationId = navigationEvent.destination,
                            inclusive = navigationEvent.inclusive
                        )
                    }
                    else -> findNavController().popBackStack()
                }
            }
        }
    }

}