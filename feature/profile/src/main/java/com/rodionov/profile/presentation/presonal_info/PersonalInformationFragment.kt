package com.rodionov.profile.presentation.presonal_info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.domain.models.User
import com.rodionov.profile.R
import com.rodionov.profile.data.factory.ProfileViewModelFactory
import com.rodionov.profile.databinding.FragmentPersonalInformationBinding
import dagger.Lazy
import javax.inject.Inject

class PersonalInformationFragment : Fragment(R.layout.fragment_personal_information) {

    @Inject
    lateinit var viewModelFactory: Lazy<ProfileViewModelFactory>

    private val binding: FragmentPersonalInformationBinding by viewBinding(
        FragmentPersonalInformationBinding::bind
    )

    private val viewModel: PersonalInformationViewModel by viewModels { viewModelFactory.get() }

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