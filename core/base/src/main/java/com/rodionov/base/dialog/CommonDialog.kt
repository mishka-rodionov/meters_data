package com.rodionov.base.dialog

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.rodionov.base.R

open class CommonDialog<T>(var handleCallback: ((T) -> Unit)? = null): DialogFragment() {

//    override fun onCreateDialog(savedInstanceState: Bundle?) =
//        super.onCreateDialog(savedInstanceState).apply {
//            Log.d("LOG_TAG", "onCreateDialog: ")
//            setCanceledOnTouchOutside(false)
//            setCancelable(false)
//            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//            requestWindowFeature(Window.FEATURE_NO_TITLE)
//        }


    override fun getTheme(): Int {
        return com.rodionov.ui.R.style.DialogTheme
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    fun show(fragmentManager: FragmentManager, tag: String? = null, callback: ((T) -> Unit)? = null) {
        handleCallback = callback
        show(fragmentManager, tag)
    }

    protected fun handleResult(value: T) {
        handleCallback?.invoke(value)
    }

}