package com.rodionov.login.presentation.login

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.rodionov.base.platform.BaseFragment
import com.rodionov.login.R
import com.rodionov.login.di.LoginComponentViewModel

class LoginFragment: Fragment(R.layout.login_fragment) {

    override fun onAttach(context: Context) {
//        ViewModelProvider(this).get<LoginComponentViewModel>().loginComponent.inject(this)
        super.onAttach(context)
    }

}