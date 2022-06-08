package com.rodionov.meter_creator.presentation.meter_editor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.base.platform.BaseViewModel
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterInfo
import com.rodionov.domain.models.MeterType
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentMeterCreatorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.meter_creator.presentation.flat_editor.FlatEditorFragment.Companion.METER
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MeterEditorFragment : BaseFragment(R.layout.fragment_meter_creator) {

    private val binding: FragmentMeterCreatorBinding by viewBinding(FragmentMeterCreatorBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<MeterCreatorViewModelFactory>

    private val viewModel: MeterEditorViewModel by viewModels {
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
        viewModel.getMeterInfo(arguments?.getParcelable<Meter>(METER)?.id ?: "")
        viewModel.meterInfo.onEach(::handleMeterInfo)
            .launchWithLifecycleStarted(lifecycleScope, lifecycle)
        viewModel.isSaveMeterInfo.onEach(::handleIsSaveMeterInfo)
            .launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val meter = arguments?.getParcelable<Meter>(METER) ?: return
        binding.actvMeterType.setText(arguments?.getParcelable<Meter>(METER)?.type?.meterName)
        binding.etMeterSerialNumber.setText(arguments?.getParcelable<Meter>(METER)?.serialNumber)
        binding.etMeterName.setText(arguments?.getParcelable<Meter>(METER)?.name)
        binding.btnSaveMeter.setOnClickListener {
            viewModel.saveMeter(meter =
            Meter(
                id = meter.id,
                name = binding.etMeterName.text.toString(),
                type = MeterType.values()
                    .find { it.meterName == binding.actvMeterType.text.toString() }
                    ?: return@setOnClickListener,
                serialNumber = binding.etMeterSerialNumber.text.toString(),
                meterUnit = MeterType.values()
                    .find { it.meterName == binding.actvMeterType.text.toString() }?.units
                    ?: return@setOnClickListener,
                dateOfManufacture = meter.dateOfManufacture,
                dateOfVerification = meter.dateOfVerification
            ), meterInfo = MeterInfo(
                meterId = meter.id,
                dataSendDay = binding.tvDayOfDataSend.text.toString().toInt(),
                payRate = binding.etPayRate.text.toString().toDouble(),
                dataSendType = binding.actvSendDataType.text.toString(),
                email = binding.etSendDataEmail.text.toString(),
                company = binding.etCompanyName.text.toString()
            )
            )
        }
        binding.actvMeterType.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                MeterType.values().map { it.meterName }
            )
        )
        binding.actvSendDataType.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                arrayOf("Email")
            )
        )
        binding.sbDay.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    binding.tvDayOfDataSend.text = p1.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    binding.tvDayOfDataSend.text = p0?.progress.toString()
                }

            }
        )
    }

    private fun handleMeterInfo(meterInfo: MeterInfo) {
        binding.tvDayOfDataSend.text = meterInfo.dataSendDay.toString()
        binding.sbDay.progress = meterInfo.dataSendDay
        binding.etPayRate.setText(meterInfo.payRate.toString())
        binding.actvSendDataType.setText(meterInfo.dataSendType)
        binding.etSendDataEmail.setText(meterInfo.email)
        binding.etCompanyName.setText(meterInfo.company)
    }

    private fun handleIsSaveMeterInfo(isSave: Boolean) {
        if (isSave) {
            viewModel.navigate(R.id.action_meterEditorFragment_to_startCreatorFragment)
        }
    }


}