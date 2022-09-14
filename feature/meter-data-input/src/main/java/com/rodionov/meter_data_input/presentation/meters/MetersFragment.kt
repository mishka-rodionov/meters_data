package com.rodionov.meter_data_input.presentation.meters

import androidx.fragment.app.viewModels
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_data_input.R
import dagger.Lazy
import javax.inject.Inject

class MetersFragment: BaseFragment(R.layout.fragment_meters) {

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: MetersViewModel by viewModels { viewModelFactory.get() }
}