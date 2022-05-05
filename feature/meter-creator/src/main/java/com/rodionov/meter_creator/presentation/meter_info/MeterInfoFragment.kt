package com.rodionov.meter_creator.presentation.meter_info

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.databinding.FragmentMeterInfoBinding

class MeterInfoFragment: BaseFragment(R.layout.fragment_meter_info) {

    private val binding: FragmentMeterInfoBinding by viewBinding(FragmentMeterInfoBinding::bind)

    private val viewModel: MeterInfoViewModel by viewModels()

    override val screenViewModel: BaseViewModel by lazy { viewModel }
}