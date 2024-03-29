package com.rodionov.meter_creator.presentation.flat_creator

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentFlatCreatorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.meter_creator.presentation.meter_creator.MeterCreatorViewModel
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FlatCreatorFragment : BaseFragment(R.layout.fragment_flat_creator) {

    private val binding: FragmentFlatCreatorBinding by viewBinding(FragmentFlatCreatorBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<MeterCreatorViewModelFactory>

    private val viewModel: FlatCreatorViewModel by viewModels { viewModelFactory.get() }

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override val toolbarTitle = R.string.toolbar_title_flat_creator

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CreatorViewModel>().creatorComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClearError()
        binding.btnSaveFlat.setOnClickListener {
            if (validate()) {
                viewModel.createFlat(
                    name = binding.etFlatName.text.toString(),
                    address = binding.etFlatAddress.text.toString()
                )
            }
        }
        viewModel.flatCreated.onEach(::handleFlatCreated)
            .launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    private fun handleFlatCreated(id: String) {
        viewModel.navigate(
            R.id.action_flatCreatorFragment_to_meterCreatorFragment, bundle = bundleOf(FLAT_ID to id)
        )
    }

    private fun validate(): Boolean {
        return validateField(binding.tilFlatAddress) and validateField(binding.tilFlatName)
    }

    private fun setClearError() {
        clearError(binding.tilFlatName)
        clearError(binding.tilFlatAddress)
    }

    companion object {
        const val FLAT_ID = "FLAT_ID"
    }

}