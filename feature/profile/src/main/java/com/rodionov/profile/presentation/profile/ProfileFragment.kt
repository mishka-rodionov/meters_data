package com.rodionov.profile.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.interaction.NavigationExecutor
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.User
import com.rodionov.profile.R
import com.rodionov.profile.data.di.ProfileComponentViewModel
import com.rodionov.profile.data.factory.ViewModelFactory
import com.rodionov.profile.databinding.FragmentProfileBinding
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    override val toolbarTitle = R.string.toolbar_title_profile

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: ProfileViewModel by viewModels {
        viewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ProfileComponentViewModel>().profileComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenViewModel.user.onEach(::handleUser).launchWithLifecycleStarted(lifecycleScope, lifecycle)
        screenViewModel.exit.onEach(::handleExit).launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvPersonalInfo.setOnClickListener { screenViewModel.navigate(R.id.action_profileFragment_to_personalInformationFragment) }
        binding.tvFlatInfo.setOnClickListener { screenViewModel.navigate(R.id.action_profileFragment_to_flatListFragment) }
        binding.tvExit.setOnClickListener { screenViewModel.exit() }
        screenViewModel.getDefaultUser()
    }

    private fun handleUser(user: User) {
        binding.tvUserFirstName.text = user.firstName.replaceFirstChar { it.uppercaseChar() }
        binding.tvUserLastName.text = user.lastName.replaceFirstChar { it.uppercaseChar() }
    }

    private fun handleExit(exit: Boolean) {
        (requireActivity() as NavigationExecutor).startActivity()
    }

}