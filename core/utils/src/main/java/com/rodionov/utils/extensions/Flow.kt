package com.rodionov.utils.extensions

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted {
        this@launchWhenStarted.collect()
    }
}

fun <T> Flow<T>.launchWithLifecycleStarted(
    lifecycleScope: LifecycleCoroutineScope,
    lifecycle: Lifecycle
) {
    lifecycleScope.launch {
        Log.d("LOG_TAG", "launchWithLifecycleStarted:  ${this::class.simpleName} ${lifecycle.currentState}")
        this@launchWithLifecycleStarted.flowWithLifecycle(lifecycle = lifecycle).collect()
    }
}

fun <T> Flow<T>.launchWithLifecycleCreated(
    lifecycleScope: LifecycleCoroutineScope,
    lifecycle: Lifecycle
) {
    lifecycleScope.launch {
        Log.d("LOG_TAG", "launchWithLifecycleStarted:  ${this::class.simpleName} ${lifecycle.currentState}")
        this@launchWithLifecycleCreated.flowWithLifecycle(lifecycle = lifecycle, minActiveState = Lifecycle.State.CREATED).collect()
    }
}