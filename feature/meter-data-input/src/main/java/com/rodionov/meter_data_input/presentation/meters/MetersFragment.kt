package com.rodionov.meter_data_input.presentation.meters

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.data.dto.MeterItem
import com.rodionov.meter_data_input.databinding.FragmentMetersBinding
import com.rodionov.meter_data_input.di.DataInputViewModel
import com.rodionov.meter_data_input.presentation.meters.adapters.MeterListAdapter
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MetersFragment: BaseFragment(R.layout.fragment_meters) {

    private val binding: FragmentMetersBinding by viewBinding(FragmentMetersBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: MetersViewModel by viewModels { viewModelFactory.get() }

    private val adapter: MeterListAdapter by lazy { MeterListAdapter{
        screenViewModel.navigate(R.id.meterInputFragment)
    } }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DataInputViewModel>().dataInputComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenViewModel.getMeters()
        screenViewModel.metersFlow.onEach { meters ->
            adapter.itemList = meters
        }.launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMeters.adapter = adapter
//        binding.btnButtonMeters.setOnClickListener {
//            screenViewModel.getMeters()
//        }
    }


}