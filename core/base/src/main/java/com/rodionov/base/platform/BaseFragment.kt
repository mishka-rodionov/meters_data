package com.rodionov.base.platform

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.rodionov.base.R
import com.rodionov.base.navigation.NavigationEvent
import com.rodionov.base.state.State
import com.rodionov.utils.extensions.launchWhenStarted
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onEach
import kotlin.properties.Delegates

open class BaseFragment(@LayoutRes val layout: Int) : Fragment(layout) {

    open val screenViewModel: BaseViewModel by Delegates.notNull()

    private val navigationController: NavController by lazy { findNavController() }

    open val toolbarTitle: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LOG_TAG", "onCreate: name = ${this.javaClass.simpleName}")
//        screenViewModel.count.onEach { Log.d("LOG_TAG", "onViewCreated: name = ${this.javaClass.simpleName}, count = $it") }.launchWithLifecycleStarted(lifecycleScope, lifecycle)
        screenViewModel.navigate.onEach(::handleNavigationEvent)
            .launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle()
    }

    private fun setToolbarTitle() {
        (requireActivity() as BaseActivity).supportActionBar?.title = toolbarTitle?.let { getString(it) }
    }

    private fun handleNavigationEvent(navigationEvent: NavigationEvent) {
        Log.d(
            "LOG_TAG",
            "handleNavigationEvent: fragment = ${this.javaClass.simpleName} $navigationEvent"
        )
        when (navigationEvent) {
            is NavigationEvent.Navigate -> {
                navigationController.navigate(
                    resId = navigationEvent.destination,
                    args = navigationEvent.bundle,
                    navOptions = navigationEvent.navOptions
                )
            }
            is NavigationEvent.Back -> {
                when {
                    navigationEvent.destination != null && navigationEvent.inclusive != null && navigationEvent.saveState != null -> {
                        navigationController.popBackStack(
                            destinationId = navigationEvent.destination,
                            inclusive = navigationEvent.inclusive,
                            saveState = navigationEvent.saveState
                        )
                    }
                    navigationEvent.destination != null && navigationEvent.inclusive != null && navigationEvent.saveState == null -> {
                        navigationController.popBackStack(
                            destinationId = navigationEvent.destination,
                            inclusive = navigationEvent.inclusive
                        )
                    }
                    else -> navigationController.popBackStack()
                }
            }
        }
    }

    private fun handleState(state: State) {
        when (state) {
            is State.Loading -> {}
            is State.Loaded -> {}
            is State.Error -> {}
        }
    }

    protected fun validateField(
        til: TextInputLayout,
        condition: Boolean = til.editText?.text.toString().trim().isEmpty(),
        errorText: Int = com.rodionov.ui.R.string.error_empty_field
    ): Boolean {
        if (condition) {
            til.error = getString(errorText)
            return false
        }
        return true
    }

    protected fun clearError(til: TextInputLayout) {
        til.editText?.doAfterTextChanged {
            if (til.isErrorEnabled and !til.error.isNullOrEmpty()) {
                til.error = ""
                til.isErrorEnabled = false
            }
        }
    }


}