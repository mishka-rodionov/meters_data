package com.rodionov.profile.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.profile.R
import com.rodionov.profile.data.di.ProfileComponentViewModel
import com.rodionov.profile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ProfileComponentViewModel>().profileComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvPersonalInfo.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_personalInformationFragment) }
        binding.tvFlatInfo.setOnClickListener { findNavController().navigate(R.id.action_profileFragment_to_flatListFragment) }
    }

}