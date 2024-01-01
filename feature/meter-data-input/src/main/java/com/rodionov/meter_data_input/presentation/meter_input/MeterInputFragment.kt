package com.rodionov.meter_data_input.presentation.meter_input

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.base.factory.CommonViewModelFactory
import com.rodionov.base.platform.BaseFragment
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.databinding.FragmentMeterInputBinding
import com.rodionov.meter_data_input.di.DataInputViewModel
import com.rodionov.meter_data_input.presentation.meters.MetersFragment.Companion.METER_ID
import com.rodionov.utils.extensions.launchWhenStarted
import com.rodionov.utils.extensions.nextFocusAfterFullFill
import com.rodionov.utils.extensions.showSoftInputKeyboard
import com.rodionov.utils.extensions.trimmedText
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MeterInputFragment : BaseFragment(R.layout.fragment_meter_input) {

    override val toolbarTitle = R.string.toolbar_title_meter_input

    private val binding: FragmentMeterInputBinding by viewBinding(FragmentMeterInputBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<CommonViewModelFactory>

    override val screenViewModel: MeterInputViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DataInputViewModel>().dataInputComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etThousandsDigit.requestFocus()
        binding.etThousandsDigit.showSoftInputKeyboard(requireContext())
        val meterID = arguments?.getString(METER_ID) ?: return
        nextFocusCorresponding()
        binding.btnSaveData.setOnClickListener {
            screenViewModel.saveData(
                meterData = calculateMeterValue(),
                meterId = meterID
            )
        }
        screenViewModel.meterInputEvents.onEach(::handleMeterInputEvent)
            .launchWhenStarted(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleMeterInputEvent(event: String) {
        findNavController().popBackStack()
    }

    private fun nextFocusCorresponding() {
        binding.etThousandsDigit.nextFocusAfterFullFill(binding.etHundredsDigit, 1)
        binding.etHundredsDigit.nextFocusAfterFullFill(binding.etTensDigit, 1)
        binding.etTensDigit.nextFocusAfterFullFill(binding.etUnitsDigit, 1)
        binding.etUnitsDigit.nextFocusAfterFullFill(binding.etTenthsDigit, 1)
        binding.etTenthsDigit.nextFocusAfterFullFill(binding.etHundredthsDigit, 1)
        binding.etHundredthsDigit.nextFocusAfterFullFill(binding.etThousandthsDigit, 1)
    }

    private fun calculateMeterValue(): Double {
        with(binding) {
            val meterDataString = listOf(
                etThousandsDigit,
                etHundredsDigit,
                etTensDigit,
                etUnitsDigit,
                etTenthsDigit,
                etHundredthsDigit,
                etThousandthsDigit
            ).joinToString(separator = "") { createOverallString(it.trimmedText()) }
            val meterData = "${meterDataString.take(4)}.${meterDataString.takeLast(3)}"
            Log.d("LOG_TAG", "calculateMeterValue: ${meterData.toDouble()}")
            return meterData.toDouble()
        }
    }

    private fun createOverallString(digit: String) = digit.takeIf { it.isNotEmpty() } ?: "0"

}