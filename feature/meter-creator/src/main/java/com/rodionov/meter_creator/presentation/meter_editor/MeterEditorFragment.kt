package com.rodionov.meter_creator.presentation.meter_editor

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.MeterInfo
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentMeterCreatorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MeterEditorFragment: BaseFragment(R.layout.fragment_meter_creator) {

    private val binding: FragmentMeterCreatorBinding by viewBinding(FragmentMeterCreatorBinding::bind)

    private val parentViewModel: CreatorViewModel by viewModels()

    @Inject
    lateinit var viewModelFactory: Lazy<MeterCreatorViewModelFactory>

    private val viewModel: MeterEditorViewModel by viewModels{
        viewModelFactory.get()
    }

    override val screenViewModel: BaseViewModel by lazy { viewModel }

    override val toolbarTitle = R.string.toolbar_title_meter_editor

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CreatorViewModel>().creatorComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMeterInfo(parentViewModel.meter.id)
        viewModel.meterInfo.onEach(::handleMeterInfo).launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actvMeterType.setText(parentViewModel.meter.type.meterName)
        binding.etMeterSerialNumber.setText(parentViewModel.meter.serialNumber)
        binding.etMeterName.setText(parentViewModel.meter.name)
    }

    private fun saveMeter()

    private fun handleMeterInfo(meterInfo: MeterInfo) {
        binding.tvDayOfDataSend.text = meterInfo.dataSendDay.toString()
        binding.sbDay.progress = meterInfo.dataSendDay
        binding.etPayRate.setText(meterInfo.payRate.toString())
        binding.actvSendDataType.setText(meterInfo.dataSendType)
        binding.etSendDataEmail.setText(meterInfo.email)
        binding.etCompanyName.setText(meterInfo.company)
    }



}