package com.rodionov.meter_creator.presentation.meter_creator

import androidx.fragment.app.viewModels
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.R

class MeterCreatorFragment: BaseFragment(R.layout.fragment_meter_creator) {



    private val viewModel: MeterCreatorViewModel by viewModels()

    override val screenViewModel: BaseViewModel by lazy { viewModel }

}