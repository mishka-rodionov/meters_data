package com.rodionov.utils.extensions

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

fun EditText.nextFocusAfterFullFill(nextEditTExt: EditText, completeLength: Int) {
    doAfterTextChanged {
        if (it.toString().trim().length == completeLength) {
            nextEditTExt.requestFocus()
        }
    }
}

fun EditText.trimmedText(): String {
    return text.toString().trim()
}
