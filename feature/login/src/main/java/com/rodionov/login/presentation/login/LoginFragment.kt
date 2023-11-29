package com.rodionov.login.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.interaction.NavigationExecutor
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.login.R
import com.rodionov.login.databinding.FragmentLoginBinding
import com.rodionov.login.di.LoginComponentViewModel
//import com.rodionov.login.di.LoginViewModelFactory
import dagger.Lazy
import javax.inject.Inject

class LoginFragment: BaseFragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    private val viewModel: LoginViewModel by viewModels { viewModelFactory.get() }

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<LoginComponentViewModel>().loginComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser()
        binding.btnRegistration.setOnClickListener { viewModel.navigate(R.id.registrationFragment) }
        binding.btnLogin.setOnClickListener {
            (requireActivity() as NavigationExecutor).startActivity()
        }
        viewModel.test()
    }

}