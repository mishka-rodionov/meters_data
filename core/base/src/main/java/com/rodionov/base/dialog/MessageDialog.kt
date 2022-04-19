package com.rodionov.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.databinding.FragmentMessageDialogBinding

class MessageDialog private constructor(
    private var title: String? = null,
    private var message: String? = null,
    private var buttonText: String? = null
) : CommonDialog<Unit>() {

    private val binding: FragmentMessageDialogBinding by viewBinding(FragmentMessageDialogBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.tvTitle.text = title
        binding.tvMessage.text = message
        binding.btnOk.text = buttonText
        return binding.root
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