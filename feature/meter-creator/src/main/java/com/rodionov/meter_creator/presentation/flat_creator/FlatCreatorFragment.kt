package com.rodionov.meter_creator.presentation.flat_creator

import androidx.fragment.app.viewModels
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.meter_creator.R

class FlatCreatorFragment: BaseFragment(R.layout.fragment_flat_creator) {

    override val screenViewModel: FlatCreatorViewModel by viewModels()

}