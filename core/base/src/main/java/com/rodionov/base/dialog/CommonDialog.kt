package com.rodionov.base.dialog

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

open class CommonDialog<T>(private var handleCallback: ((T) -> Unit)? = null): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(false)
            setCancelable(false)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    fun show(fragmentManager: FragmentManager, tag: String, callback: ((T) -> Unit)? = null) {
        handleCallback = callback
        show(fragmentManager, tag)
    }

    protected fun handleResult(value: T) {
        handleCallback?.invoke(value)
    }

}