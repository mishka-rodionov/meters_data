package com.rodionov.utils.extensions

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

fun EditText.nextFocusAfterFullFill(nextEditTExt: EditText, completeLength: Int) {
    this.doAfterTextChanged {
        if (it.toString().trim().length == completeLength) {
            nextEditTExt.requestFocus()
        }
    }
}
