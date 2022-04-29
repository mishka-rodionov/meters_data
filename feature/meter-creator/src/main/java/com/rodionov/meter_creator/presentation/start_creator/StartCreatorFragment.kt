package com.rodionov.meter_creator.presentation.start_creator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.databinding.FragmentMeterCreatorBinding

class StartCreatorFragment: BaseFragment(R.layout.fragment_meter_creator) {

    private val binding: FragmentMeterCreatorBinding by viewBinding(FragmentMeterCreatorBinding::bind)

    private val viewModel: StartCreatorViewModel by viewModels()

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mcvFlatCreator.setOnClickListener {
            viewModel.navigate(R.id.action_meterCreatorFragment_to_flatCreatorFragment)
        }
    }

}