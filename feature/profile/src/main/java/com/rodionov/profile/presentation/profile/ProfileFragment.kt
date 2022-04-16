package com.rodionov.profile.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.profile.R
import com.rodionov.profile.data.di.ProfileComponentViewModel
import com.rodionov.profile.data.factory.ProfileViewModelFactory
import com.rodionov.profile.databinding.FragmentProfileBinding
import dagger.Lazy
import javax.inject.Inject

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<ProfileViewModelFactory>

    private val viewModel: ProfileViewModel by viewModels {
        viewModelFactory.get()
    }

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ProfileComponentViewModel>().profileComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvPersonalInfo.setOnClickListener { viewModel.navigate(R.id.action_profileFragment_to_personalInformationFragment) }
        binding.tvFlatInfo.setOnClickListener { viewModel.navigate(R.id.action_profileFragment_to_flatListFragment) }
        viewModel.getDefaultUser()
    }

}