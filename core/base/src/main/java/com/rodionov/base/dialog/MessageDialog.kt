package com.rodionov.base.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.databinding.FragmentMessageDialogBinding

class MessageDialog() : CommonDialog<Unit>() {

    private constructor(
        title: String? = null,
        message: String? = null,
        buttonText: String? = null
    ) : this() {
        this.title = title
        this.message = message
        this.buttonText = buttonText
    }

    private var title: String? = null
    private var message: String? = null
    private var buttonText: String? = null
//    private var handleCallback: ((Unit) -> Unit)? = null

    //    private val binding: FragmentMessageDialogBinding by viewBinding(FragmentMessageDialogBinding::bind)
    lateinit var binding: FragmentMessageDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LOG_TAG", "onViewCreated: title = $title")
        binding.tvTitle.text = title
        binding.tvMessage.text = message
        binding.btnOk.text = buttonText
        binding.btnOk.setOnClickListener { close() }
    }

    private fun close() {
        handleResult(Unit)
        dismiss()
    }

    class Builder {
        private var title: String? = null
        private var message: String? = null
        private var buttonText: String = ""

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