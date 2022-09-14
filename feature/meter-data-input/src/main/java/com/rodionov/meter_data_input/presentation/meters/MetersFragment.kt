package com.rodionov.meter_data_input.presentation.meters

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.di.DataInputViewModel
import dagger.Lazy
import javax.inject.Inject

class MetersFragment: BaseFragment(R.layout.fragment_meters) {

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: MetersViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DataInputViewModel>().dataInputComponent.inject(this)
        super.onAttach(context)
    }
}