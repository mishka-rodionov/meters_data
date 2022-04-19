package com.rodionov.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MessageDialog private constructor(
    private var title: String? = null,
    private var message: String? = null,
    private var buttonText: String? = null
) : CommonDialog<Unit>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    inner class Builder {
        private var title: String? = null
        private var message: String? = null
        private var buttonText: String? = null

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun message(message: String): Builder {
            this.message = message
            return this
        }

        fun buttonText(buttonText: String): Builder {
            this.buttonText = buttonText
            return this
        }

        fun build() = MessageDialog(title, message, buttonText)

    }

}