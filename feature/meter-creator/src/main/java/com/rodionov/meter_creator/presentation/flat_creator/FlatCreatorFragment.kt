package com.rodionov.meter_creator.presentation.flat_creator

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentFlatCreatorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.meter_creator.presentation.meter_creator.MeterCreatorViewModel
import dagger.Lazy
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
                screenViewModel.navigate(R.id.action_flatCreatorFragment_to_meterCreatorFragment)
            }
        }
    }

    private fun validate(): Boolean {
        return validateField(binding.tilFlatAddress) && validateField(binding.tilFlatName)
    }

    private fun setClearError() {
        clearError(binding.tilFlatName)
        clearError(binding.tilFlatAddress)
    }

}