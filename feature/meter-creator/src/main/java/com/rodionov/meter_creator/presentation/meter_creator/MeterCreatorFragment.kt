package com.rodionov.meter_creator.presentation.meter_creator

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.databinding.FragmentMeterCreatorBinding

class MeterCreatorFragment: BaseFragment(R.layout.fragment_meter_creator) {

    private val binding: FragmentMeterCreatorBinding by viewBinding(FragmentMeterCreatorBinding::bind)

    private val viewModel: MeterCreatorViewModel by viewModels()

    override val screenViewModel by lazy { viewModel }

}