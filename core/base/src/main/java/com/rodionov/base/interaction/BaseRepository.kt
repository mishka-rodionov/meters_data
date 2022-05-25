package com.rodionov.base.interaction

import android.util.Log
import com.rodionov.base.state.ErrorHandler
import com.rodionov.base.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

open class BaseRepository(val errorHandler: ErrorHandler) {

    protected suspend inline fun <T> execute(
        crossinline onSuccess: (T) -> Unit,
        crossinline onState: (State) -> Unit,
        noinline func: suspend () -> T
    ) {

        try {
            withContext(Dispatchers.Main) { onState.invoke(State.Loading) }

            val result = withContext(Dispatchers.IO) { func.invoke() }

            withContext(Dispatchers.Main) {
                onSuccess.invoke(result)
                onState.invoke(State.Loaded)
            }
        } catch (e: Throwable) {
            Log.d("LOG_TAG", "execute: ")
            withContext(Dispatchers.Main) {
                onState.invoke(State.Error(errorHandler.proceedException(e)))
            }
        }

    }

}