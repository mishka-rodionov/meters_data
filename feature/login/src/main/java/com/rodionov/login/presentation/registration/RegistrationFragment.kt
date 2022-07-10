package com.rodionov.login.presentation.registration

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.login.R
import com.rodionov.login.databinding.FragmentRegistrationBinding
import com.rodionov.login.di.LoginComponentViewModel
import dagger.Lazy
import javax.inject.Inject

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: RegistrationViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<LoginComponentViewModel>().loginComponent.inject(this)
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearAllErrors()
        screenViewModel.test()
        binding.btnRegistrationBack.setOnClickListener { screenViewModel.back() }
        binding.btnRegistrationComplete.setOnClickListener {
            if (validate() and validatePasswords()) {
                Log.d("LOG_TAG", "onViewCreated: all field is valid")
            }
        }
    }

    private fun validatePasswords() =
        validateField(
            binding.tilRepeatRegistrationPassword,
            binding.etRegistrationPassword.text.toString()
                .trim() != binding.etRepeatRegistrationPassword.text.toString().trim(),
            com.rodionov.ui.R.string.error_password_not_equals
        )

    private fun validate() =
        validateField(binding.tilRegistrationLogin) and validateField(binding.tilFirstName) and validateField(
            binding.tilSecondName
        ) and validateField(binding.tilRegistrationPassword) and validateField(binding.tilRepeatRegistrationPassword)

    private fun clearAllErrors() {
        clearError(binding.tilRegistrationLogin)
        clearError(binding.tilFirstName)
        clearError(binding.tilSecondName)
        clearError(binding.tilRegistrationPassword)
        clearError(binding.tilRepeatRegistrationPassword)
    }

}