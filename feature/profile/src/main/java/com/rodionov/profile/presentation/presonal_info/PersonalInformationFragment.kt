package com.rodionov.profile.presentation.presonal_info

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.User
import com.rodionov.profile.R
import com.rodionov.profile.data.di.ProfileComponentViewModel
import com.rodionov.profile.data.factory.ProfileViewModelFactory
import com.rodionov.profile.databinding.FragmentPersonalInformationBinding
import dagger.Lazy
import javax.inject.Inject

class PersonalInformationFragment : BaseFragment(R.layout.fragment_personal_information) {

    @Inject
    lateinit var viewModelFactory: Lazy<ProfileViewModelFactory>

    private val binding: FragmentPersonalInformationBinding by viewBinding(
        FragmentPersonalInformationBinding::bind
    )

    private val viewModel: PersonalInformationViewModel by viewModels { viewModelFactory.get() }

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ProfileComponentViewModel>().profileComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaveUser.setOnClickListener {
            viewModel.setUser(
                User(
                    id = "",
                    firstName = binding.etPersonalInfoFirstName.text.toString(),
                    lastName = binding.etPersonalInfoLastName.text.toString(),
                    phone = binding.etPersonalInfoPhone.text.toString(),
                    email = ""
                )
            )
        }
    }

}