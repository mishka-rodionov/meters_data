package com.rodionov.meter_creator.presentation.meter_creator

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.SeekBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.platform.BaseFragment
import com.rodionov.domain.models.MeterType
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.data.factory.MeterCreatorViewModelFactory
import com.rodionov.meter_creator.databinding.FragmentMeterCreatorBinding
import com.rodionov.meter_creator.di.CreatorViewModel
import com.rodionov.meter_creator.presentation.flat_creator.FlatCreatorFragment.Companion.FLAT_ID
import com.rodionov.utils.extensions.launchWithLifecycleStarted
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MeterCreatorFragment : BaseFragment(R.layout.fragment_meter_creator) {

    private val binding: FragmentMeterCreatorBinding by viewBinding(FragmentMeterCreatorBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<MeterCreatorViewModelFactory>

    private val viewModel: MeterCreatorViewModel by viewModels { viewModelFactory.get() }

    override val screenViewModel by lazy { viewModel }

    override val toolbarTitle = R.string.toolbar_title_meter_creator

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<CreatorViewModel>().creatorComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClearError()
        Log.d("LOG_TAG", "onViewCreated: id = ${arguments?.getString(FLAT_ID)}")
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
        binding.btnSaveMeter.setOnClickListener {
            viewModel.navigate(R.id.action_meterCreatorFragment_to_meterInfoFragment)
        }
        binding.tvDayOfDataSend.text = binding.sbDay.progress.toString()
        binding.sbDay.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    Log.d(
                        "LOG_TAG",
                        "onProgressChanged: progress = ${p0?.progress}, p1 = $p1, p2 = $p2"
                    )
                    binding.tvDayOfDataSend.text = p1.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                    Log.d("LOG_TAG", "onStartTrackingTouch: progress  = ${p0?.progress}")

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                    Log.d("LOG_TAG", "onStopTrackingTouch: progress = ${p0?.progress}")
                    binding.tvDayOfDataSend.text = p0?.progress.toString()
                }

            }
        )
        binding.btnSaveMeter.setOnClickListener {
            if (validate()) {
                viewModel.createAndSaveMeter(
                    flatId = arguments?.getString(FLAT_ID) ?: return@setOnClickListener, // TODO replace to real null check
                    meterType = MeterType.values()
                        .find { it.meterName == binding.actvMeterType.text.toString() }
                        ?: return@setOnClickListener,
                    serialNumber = binding.etMeterSerialNumber.text.toString(),
                    meterName = binding.etMeterName.text.toString(),
                    dataSendDay = binding.tvDayOfDataSend.text.toString().toInt(),
                    payRate = binding.etPayRate.text.toString().toDouble(),
                    dataSendType = binding.actvSendDataType.text.toString(),
                    company = binding.etCompanyName.text.toString()
                )
            }
        }
        viewModel.finish.onEach {
            Log.d("LOG_TAG", "onViewCreated: viewModel.back ")
            viewModel.back() }.launchWithLifecycleStarted(lifecycleScope, lifecycle)
    }

    private fun validate(): Boolean {
        var result = true
        listOf(
            binding.tilMeterType,
            binding.tilMeterName,
            binding.tilMeterSerialNumber,
            binding.tilPayRate,
            binding.tilSendDataType,
            binding.tilCompanyName
        ).forEach {
            result = result and validateField(it)
        }
        return result
    }

    private fun setClearError() {
        clearError(binding.tilMeterType)
        clearError(binding.tilMeterName)
        clearError(binding.tilMeterSerialNumber)
        clearError(binding.tilPayRate)
        clearError(binding.tilSendDataType)
        clearError(binding.tilCompanyName)
    }

}