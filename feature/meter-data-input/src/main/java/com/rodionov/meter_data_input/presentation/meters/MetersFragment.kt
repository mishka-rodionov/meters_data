package com.rodionov.meter_data_input.presentation.meters

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.databinding.FragmentMetersBinding
import com.rodionov.meter_data_input.di.DataInputViewModel
import dagger.Lazy
import javax.inject.Inject

class MetersFragment: BaseFragment(R.layout.fragment_meters) {

    private val binding: FragmentMetersBinding by viewBinding(FragmentMetersBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: MetersViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DataInputViewModel>().dataInputComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnButtonMeters.setOnClickListener {
            screenViewModel.getMeters()
        }
    }


}