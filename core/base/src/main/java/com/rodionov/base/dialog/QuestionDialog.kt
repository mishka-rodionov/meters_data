package com.rodionov.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodionov.base.R
import com.rodionov.base.databinding.FragmentMessageDialogBinding
import com.rodionov.base.databinding.FragmentQuestionDialogBinding

class QuestionDialog(): CommonDialog<Boolean>() {

    private constructor(
        title: String? = null,
        message: String? = null,
        buttonPositiveText: String? = null,
        buttonNegativeText: String? = null
    ) : this() {
        this.title = title
        this.message = message
        buttonPositiveText?.let { this.buttonPositiveText = buttonPositiveText }
        buttonNegativeText?.let { this.buttonNegativeText = buttonNegativeText }
    }

    private var title: String? = null
    private var message: String? = null
    private var buttonPositiveText: String? = null
    private var buttonNegativeText: String? = null

    lateinit var binding: FragmentQuestionDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitleQuestionDialog.text = title
        binding.tvMessageQuestionDialog.text = message
        buttonPositiveText?.let { binding.btnQuestionDialogPositive.text = it }
        buttonNegativeText?.let { binding.btnQuestionDialogNegative.text = it }
        binding.btnQuestionDialogPositive.setOnClickListener { close(true) }
        binding.btnQuestionDialogNegative.setOnClickListener { close(false) }
    }

    private fun close(value: Boolean) {
        handleResult(value)
        dismiss()
    }

    class Builder {
        private var title: String? = null
        private var message: String? = null
        private var buttonPositiveText: String? = null
        private var buttonNegativeText: String? = null

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun message(message: String): Builder {
            this.message = message
            return this
        }

        fun buttonPositiveText(buttonText: String): Builder {
            this.buttonPositiveText = buttonText
            return this
        }

        fun buttonNegativeText(buttonText: String): Builder {
            this.buttonNegativeText = buttonText
            return this
        }

        fun build() = QuestionDialog(title, message, buttonPositiveText, buttonNegativeText)

    }

}