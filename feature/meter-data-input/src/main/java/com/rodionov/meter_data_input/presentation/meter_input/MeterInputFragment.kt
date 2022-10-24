package com.rodionov.meter_data_input.presentation.meter_input

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.databinding.FragmentMeterInputBinding
import com.rodionov.meter_data_input.di.DataInputViewModel
import dagger.Lazy
import javax.inject.Inject

class MeterInputFragment: BaseFragment(R.layout.fragment_meter_input) {

    override val toolbarTitle = R.string.toolbar_title_meter_input

    private val binding: FragmentMeterInputBinding by viewBinding(FragmentMeterInputBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: MeterInputViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DataInputViewModel>().dataInputComponent.inject(this)
        super.onAttach(context)
    }


}